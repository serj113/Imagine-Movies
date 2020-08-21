package com.serj113.data.di

import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class DebugOkHttpClientModule {
    @Provides
    @Singleton
    internal fun provideOkHttpClient(
        flipperOkhttpInterceptor: FlipperOkhttpInterceptor
    ): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addNetworkInterceptor(flipperOkhttpInterceptor)
            .build()
    }
}