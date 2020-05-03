package com.serj113.imaginemovies.di.module

import com.serj113.imaginemovies.data.repository.MovieRepositoryImpl
import com.serj113.imaginemovies.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {
    @Binds
    internal abstract fun provideMovieRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository
}