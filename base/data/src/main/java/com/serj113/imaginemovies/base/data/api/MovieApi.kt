package com.serj113.imaginemovies.base.data.api

import com.serj113.imaginemovies.base.model.BuildConfig
import com.serj113.imaginemovies.base.model.MovieDetail
import com.serj113.imaginemovies.base.model.MovieList
import com.serj113.imaginemovies.base.model.ReviewList
import retrofit2.Response
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
    ): Response<MovieList>

    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY,
        @Query("page")
        page: Long
    ): Response<MovieList>

    @GET("movie/{id}/recommendations")
    suspend fun getMovieRecommendations(
        @Path("id")
        id: Long,
        @Query("page")
        page: Long,
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): Response<MovieList>

    @GET("movie/{id}/similar")
    suspend fun getMovieSimilar(
        @Path("id")
        id: Long,
        @Query("page")
        page: Long,
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): Response<MovieList>

    @GET("movie/latest")
    suspend fun getLatestMovie(
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): Response<MovieList>

    @GET("movie/{id}")
    suspend fun getMovieDetail(
        @Path("id")
        id: Long,
        @Query("append_to_response")
        appendToResponse: String = "credits",
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): Response<MovieDetail>

    @GET("movie/{id}/reviews")
    suspend fun getMovieReviews(
        @Path("id")
        id: Long,
        @Query("page")
        page: Long,
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): Response<ReviewList>
}