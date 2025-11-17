package com.serj113.imaginemovies.base.domain.repository

import com.serj113.model.Account
import com.serj113.model.AuthToken
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun saveLoginData(account: Account, authToken: AuthToken): Flow<Boolean>
    fun isLogin(): Flow<Boolean>
}