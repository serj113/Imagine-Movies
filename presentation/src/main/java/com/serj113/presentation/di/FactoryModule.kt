package com.serj113.presentation.di

import com.serj113.domain.interactor.FetchMovieUseCase
import com.serj113.presentation.factory.MovieFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@InstallIn(ActivityComponent::class)
@Module
class FactoryModule {
    @Provides
    @ActivityScoped
    fun provideMovieFactory(useCase: FetchMovieUseCase): MovieFactory {
        return MovieFactory(useCase)
    }
}