package com.serj113.domain.repository

import androidx.lifecycle.LiveData
import com.serj113.domain.base.PagedEntity
import com.serj113.domain.entity.Movie

interface MovieRepository {
    fun fetchMovies(): LiveData<PagedEntity<Movie>>
}