package com.serj113.domain.di

import com.serj113.domain.interactor.FetchMovieUseCase
import com.serj113.domain.usecase.FetchMovieUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
abstract class UseCaseModule {
    @Binds
    internal abstract fun provideFetchMovieUseCase(
        fetchMovieUseCaseImpl: FetchMovieUseCaseImpl
    ): FetchMovieUseCase
}