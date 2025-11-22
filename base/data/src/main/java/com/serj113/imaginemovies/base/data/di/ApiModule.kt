package com.serj113.imaginemovies.base.data.di

import com.serj113.imaginemovies.base.data.BuildConfig
import com.serj113.imaginemovies.base.data.api.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {
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
