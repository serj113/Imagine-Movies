package com.serj113.imaginemovies.di.module

import com.serj113.domain.repository.MovieRepository
import com.serj113.imaginemovies.data.repository.MovieRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {
    @Binds
    internal abstract fun provideMovieRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository
}