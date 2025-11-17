package com.serj113.imaginemovies.base.domain.repository

import com.serj113.imaginemovies.base.domain.Entity
import com.serj113.model.MovieDetail
import com.serj113.model.MovieList
import com.serj113.model.ReviewList
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun fetchMovies(page: Long): Flow<Entity<MovieList>>

    fun fetchPopularMovies(page: Long): Flow<Entity<MovieList>>

    fun fetchMovieRecommendations(movieId: Long, page: Long): Flow<Entity<MovieList>>

    fun fetchMovieSimilar(movieId: Long, page: Long): Flow<Entity<MovieList>>

    fun fetchMovieReviews(movieId: Long, page: Long): Flow<Entity<ReviewList>>

    fun fetchMovieDetail(movieId: Long): Flow<Entity<MovieDetail>>
}