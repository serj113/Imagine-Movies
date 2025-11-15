package com.serj113.base.domain.usecase

import com.serj113.base.domain.interactor.IsLoginUseCase
import com.serj113.base.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsLoginUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : IsLoginUseCase() {
    override fun invoke(args: Unit): Flow<Boolean> = userRepository.isLogin()
}