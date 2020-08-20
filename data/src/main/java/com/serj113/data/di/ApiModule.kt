package com.serj113.data.di

import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.serj113.data.BuildConfig
import com.serj113.data.api.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class ApiModule {
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

//    need to enable multidex
//    @Provides
//    @Singleton
//    internal fun provideMoshi(): Moshi {
//        return Moshi.Builder()
//            .add(KotlinJsonAdapterFactory())
//            .build()
//    }

    @Provides
    @Singleton
    internal fun provideRetrofitInterface(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    internal fun provideMovieApi(retrofit: Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }
}