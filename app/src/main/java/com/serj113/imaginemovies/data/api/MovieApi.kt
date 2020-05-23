package com.serj113.imaginemovies.data.api

import com.serj113.imaginemovies.BuildConfig
import com.serj113.imaginemovies.data.model.Response
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("discover/movie")
    fun getDiscoverMovie(
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY,

        @Query("page")
        page: Long
    ): Single<Response>

    @GET("movie/popular")
    fun getPopularMovie(
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY,

        @Query("page")
        page: Long
    ): Single<Response>

    @GET("movie/latest")
    fun getLatestMovie(
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): Single<Response>
}