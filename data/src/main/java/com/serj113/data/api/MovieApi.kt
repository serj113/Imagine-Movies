package com.serj113.data.api

import com.serj113.data.BuildConfig
import com.serj113.data.model.MovieDetailResponse
import com.serj113.data.model.MovieResponse
import com.serj113.data.model.ReviewResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("discover/movie")
    suspend fun getDiscoverMovie(
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY,

        @Query("page")
        page: Long
    ): MovieResponse

    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY,

        @Query("page")
        page: Long
    ): MovieResponse

    @GET("movie/latest")
    suspend fun getLatestMovie(
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): MovieResponse

    @GET("movie/{id}")
    suspend fun getMovieDetail(
        @Path("id")
        id: Long,
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): MovieDetailResponse

    @GET("movie/{id}/reviews")
    suspend fun getMovieReviews(
        @Path("id")
        id: Long,
        @Query("page")
        page: Long,
        @Query("append_to_response")
        appendToResponse: String = "credits",
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): ReviewResponse
}