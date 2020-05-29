package com.serj113.data.di

import com.serj113.data.BuildConfig
import com.serj113.data.api.MovieApi
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {
    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
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
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

    @Provides
    @Singleton
    internal fun provideMovieApi(retrofit: Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }
}