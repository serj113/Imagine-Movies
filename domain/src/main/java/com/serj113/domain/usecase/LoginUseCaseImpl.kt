package com.serj113.domain.usecase

import com.serj113.domain.base.Entity
import com.serj113.domain.interactor.LoginUseCase
import com.serj113.domain.repository.UserRepository
import com.serj113.model.MovieList
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : LoginUseCase() {
    override fun invoke(args: Args): Flow<Entity<MovieList>> {
        TODO("Not yet implemented")
    }
}