package com.serj113.domain.repository

import com.serj113.domain.base.PagedEntity
import com.serj113.domain.entity.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun fetchMovies(): Flow<PagedEntity<Movie>>
}