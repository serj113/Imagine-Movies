package com.serj113.base.domain.usecase

import com.serj113.base.domain.interactor.LoginUseCase
import com.serj113.base.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : LoginUseCase() {
    override fun invoke(args: Args): Flow<Boolean> {
        return userRepository.saveLoginData(args.account, args.authToken)
    }
}