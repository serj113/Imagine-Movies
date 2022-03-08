package com.serj113.domain.di

import com.serj113.domain.interactor.*
import com.serj113.domain.usecase.*
import com.serj113.domain.usecase.FetchMovieDetailUseCaseImpl
import com.serj113.domain.usecase.FetchMovieReviewUseCaseImpl
import com.serj113.domain.usecase.FetchMovieUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class UseCaseModule {
    @Binds
    @Singleton
    internal abstract fun provideFetchMovieUseCase(
        fetchMovieUseCaseImpl: FetchMovieUseCaseImpl
    ): FetchMovieUseCase

    @Binds
    @Singleton
    internal abstract fun provideFetchPopularMovieUseCase(
        fetchPopularMovieUseCaseImpl: FetchPopularMovieUseCaseImpl
    ): FetchPopularMovieUseCase

    @Binds
    @Singleton
    internal abstract fun provideFetchMovieReviewUseCase(
        fetchMovieReviewUseCaseImpl: FetchMovieReviewUseCaseImpl
    ): FetchMovieReviewUseCase

    @Binds
    @Singleton
    internal abstract fun provideFetchMovieDetailUseCase(
        fetchMovieDetailUseCaseImpl: FetchMovieDetailUseCaseImpl
    ): FetchMovieDetailUseCase

    @Binds
    @Singleton
    internal abstract fun provideIsLoginUseCase(
        isLoginUseCaseImpl: IsLoginUseCaseImpl
    ): IsLoginUseCase

    @Binds
    @Singleton
    internal abstract fun provideLoginUseCase(
        loginUseCaseImpl: LoginUseCaseImpl
    ): LoginUseCase
}
