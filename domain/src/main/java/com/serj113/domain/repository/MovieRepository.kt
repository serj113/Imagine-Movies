package com.serj113.domain.repository

import com.serj113.domain.base.Entity
import com.serj113.domain.entity.Movie
import com.serj113.domain.entity.Review
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun fetchMovies(page: Long): Flow<Entity<List<Movie>>>

    fun fetchMovieReviews(movieId: Long, page: Long): Flow<Entity<List<Review>>>
}