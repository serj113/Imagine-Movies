package com.serj113.domain.usecase

import com.serj113.domain.interactor.LogoutUseCase
import com.serj113.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LogoutUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : LogoutUseCase() {
    override fun invoke(args: Nothing): Flow<Boolean> {
        TODO("Not yet implemented")
    }
}