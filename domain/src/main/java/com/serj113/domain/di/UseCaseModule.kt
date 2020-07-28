package com.serj113.domain.di

import com.serj113.domain.interactor.FetchMovieUseCase
import com.serj113.domain.interactor.SealedFetchMovieUseCase
import com.serj113.domain.usecase.FetchMovieUseCaseImpl
import com.serj113.domain.usecase.SealedFetchMovieUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
abstract class UseCaseModule {
    @Binds
    @Singleton
    internal abstract fun provideFetchMovieUseCase(
        fetchMovieUseCaseImpl: FetchMovieUseCaseImpl
    ): FetchMovieUseCase

    @Binds
    @Singleton
    internal abstract fun provideSealedFetchMovieUseCase(
        sealedFetchMovieUseCase: SealedFetchMovieUseCaseImpl
    ): SealedFetchMovieUseCase
}