package com.serj113.domain.di

import com.serj113.domain.interactor.FetchMovieUseCase
import com.serj113.domain.usecase.FetchMovieUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
abstract class UseCaseModule {
    @Binds
    internal abstract fun provideFetchMovieUseCase(
        fetchMovieUseCaseImpl: FetchMovieUseCaseImpl
    ): FetchMovieUseCase
}