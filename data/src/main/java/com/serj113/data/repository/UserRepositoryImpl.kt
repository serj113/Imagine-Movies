package com.serj113.data.repository

import com.serj113.data.local.sharedpref.SharedPrefManager
import com.serj113.domain.repository.UserRepository
import com.serj113.model.Account
import com.serj113.model.AuthToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val sharedPrefManager: SharedPrefManager
) : UserRepository {
    override fun saveLoginData(account: Account, authToken: AuthToken): Flow<Boolean> {
        return flow {
            saveAccountToSharedPreferences(account)
            saveAuthToSharedPreferences(authToken)
            emit(true)
        }.flowOn(Dispatchers.IO)
    }

    fun saveAccountToSharedPreferences(account: Account) = GlobalScope.launch {
        with(sharedPrefManager) {
            saveText("uid", account.uid)
            saveText("display_name", account.displayName)
            saveText("email", account.email)
        }
    }

    fun saveAuthToSharedPreferences(authToken: AuthToken) = GlobalScope.launch {
        sharedPrefManager.saveText("auth_token", authToken.token)
    }
}