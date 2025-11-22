package com.serj113.imaginemovies.base.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DebugOkHttpClientModule {
    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .build()
    }
}
