package com.serj113.domain.repository

import com.serj113.domain.base.SealedEntity
import com.serj113.domain.entity.Movie
import kotlinx.coroutines.flow.Flow

interface SealedMovieRepository {
    fun fetchMovies(page: Long): Flow<SealedEntity<List<Movie>>>
}