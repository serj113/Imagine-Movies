package com.serj113.imaginemovies.di

import com.serj113.imaginemovies.wrapper.ApplicationWrapper
import com.serj113.imaginemovies.wrapper.DebugApplicationWrapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DebugApplicationWrapperModule {
    @Provides
    @Singleton
    fun provideDebugApplicationWrapper(): ApplicationWrapper {
        return DebugApplicationWrapperImpl()
    }
}
