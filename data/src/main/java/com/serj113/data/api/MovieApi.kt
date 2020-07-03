package com.serj113.data.api

import com.serj113.data.BuildConfig
import com.serj113.data.model.Response
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("discover/movie")
    suspend fun getDiscoverMovie(
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY,

        @Query("page")
        page: Long
    ): Response

    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY,

        @Query("page")
        page: Long
    ): Response

    @GET("movie/latest")
    suspend fun getLatestMovie(
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): Response
}