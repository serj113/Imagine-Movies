package com.serj113.imaginemovies.di.module

import com.serj113.domain.interactor.FetchMovieUseCase
import com.serj113.imaginemovies.domain.FetchMovieUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
abstract class UseCaseModule {
    @Binds
    internal abstract fun provideFetchMovieUseCase(
        fetchMovieUseCaseImpl: FetchMovieUseCaseImpl
    ): FetchMovieUseCase
}