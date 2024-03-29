package com.serj113.imaginemovies.di

import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class FlipperModule {
    @Provides
    @Singleton
    fun provideNetworkFlipperPlugin(): NetworkFlipperPlugin {
        return NetworkFlipperPlugin()
    }

    @Provides
    @Singleton
    fun provideFlipperOkhttpInterceptor(
        networkFlipperPlugin: NetworkFlipperPlugin
    ): FlipperOkhttpInterceptor {
        return FlipperOkhttpInterceptor(networkFlipperPlugin)
    }
}
