package com.serj113.imaginemovies.di

import com.serj113.imaginemovies.wrapper.ApplicationWrapperImpl
import com.serj113.imaginemovies.wrapper.ApplicationWrapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
open class ApplicationWrapperModule {
    @Provides
    @Singleton
    open fun provideApplicationWrapper(): ApplicationWrapper {
        return ApplicationWrapperImpl()
    }
}