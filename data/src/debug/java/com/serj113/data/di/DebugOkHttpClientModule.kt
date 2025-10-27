package com.serj113.data.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DebugOkHttpClientModule {
//    @Provides
//    @Singleton
//    internal fun provideOkHttpClient(
//        flipperOkhttpInterceptor: FlipperOkhttpInterceptor
//    ): OkHttpClient {
//        return OkHttpClient
//            .Builder()
//            .addNetworkInterceptor(flipperOkhttpInterceptor)
//            .build()
//    }
}
