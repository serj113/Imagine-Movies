package com.serj113.data.repository

import com.serj113.domain.repository.UserRepository
import com.serj113.model.Account
import com.serj113.model.AuthToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor() : UserRepository {
    override fun saveLoginData(account: Account, authToken: AuthToken): Flow<Boolean> {
        return flow {
            emit(true)
        }.flowOn(Dispatchers.IO)
    }
}