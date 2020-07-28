package com.serj113.data.di

import com.serj113.data.repository.MovieRepositoryImpl
import com.serj113.data.repository.SealedMovieRepositoryImpl
import com.serj113.domain.repository.MovieRepository
import com.serj113.domain.repository.SealedMovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    @Singleton
    internal abstract fun provideMovieRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository

    @Binds
    @Singleton
    internal abstract fun provideSealedMovieRepository(
        sealedMovieRepositoryImpl: SealedMovieRepositoryImpl
    ): SealedMovieRepository
}