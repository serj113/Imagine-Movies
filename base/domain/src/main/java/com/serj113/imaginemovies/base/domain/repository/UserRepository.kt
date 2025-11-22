package com.serj113.imaginemovies.base.domain.repository

import com.serj113.imaginemovies.base.model.Account
import com.serj113.imaginemovies.base.model.AuthToken
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun saveLoginData(account: Account, authToken: AuthToken): Flow<Boolean>
    fun isLogin(): Flow<Boolean>
}