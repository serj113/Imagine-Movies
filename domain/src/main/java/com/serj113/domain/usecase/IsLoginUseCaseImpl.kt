package com.serj113.domain.usecase

import com.serj113.domain.interactor.IsLoginUseCase
import com.serj113.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsLoginUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : IsLoginUseCase() {
    override fun invoke(args: Nothing): Flow<Boolean> {
        TODO("Not yet implemented")
    }
}