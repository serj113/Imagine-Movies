package com.serj113.imaginemovies.base.domain.di

import com.serj113.imaginemovies.base.domain.interactor.FetchMovieDetailUseCase
import com.serj113.imaginemovies.base.domain.interactor.FetchMovieRecommendationsUseCase
import com.serj113.imaginemovies.base.domain.interactor.FetchMovieReviewUseCase
import com.serj113.imaginemovies.base.domain.interactor.FetchMovieSimilarUseCase
import com.serj113.imaginemovies.base.domain.interactor.FetchMovieUseCase
import com.serj113.imaginemovies.base.domain.interactor.FetchPopularMovieUseCase
import com.serj113.imaginemovies.base.domain.interactor.IsLoginUseCase
import com.serj113.imaginemovies.base.domain.interactor.LoginUseCase
import com.serj113.imaginemovies.base.domain.usecase.FetchMovieDetailUseCaseImpl
import com.serj113.imaginemovies.base.domain.usecase.FetchMovieRecommendationsUseCaseImpl
import com.serj113.imaginemovies.base.domain.usecase.FetchMovieReviewUseCaseImpl
import com.serj113.imaginemovies.base.domain.usecase.FetchMovieSimilarUseCaseImpl
import com.serj113.imaginemovies.base.domain.usecase.FetchMovieUseCaseImpl
import com.serj113.imaginemovies.base.domain.usecase.FetchPopularMovieUseCaseImpl
import com.serj113.imaginemovies.base.domain.usecase.IsLoginUseCaseImpl
import com.serj113.imaginemovies.base.domain.usecase.LoginUseCaseImpl
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
    internal abstract fun provideFetchMovieRecommendationsUseCase(
        fetchMovieRecommendationsUseCaseImpl: FetchMovieRecommendationsUseCaseImpl
    ): FetchMovieRecommendationsUseCase

    @Binds
    @Singleton
    internal abstract fun provideFetchMovieSimilarUseCase(
        fetchMovieSimilarUseCaseImpl: FetchMovieSimilarUseCaseImpl
    ): FetchMovieSimilarUseCase

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
