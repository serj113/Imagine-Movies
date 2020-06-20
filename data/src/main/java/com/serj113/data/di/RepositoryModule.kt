package com.serj113.data.di

import com.serj113.data.repository.MovieRepositoryImpl
import com.serj113.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    internal abstract fun provideMovieRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository
}