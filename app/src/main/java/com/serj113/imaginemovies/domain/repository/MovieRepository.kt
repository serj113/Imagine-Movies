package com.serj113.imaginemovies.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.serj113.imaginemovies.domain.base.PagedEntity
import com.serj113.imaginemovies.domain.entity.Movie

interface MovieRepository {
    fun fetchMovies(): LiveData<PagedEntity<Movie>>
}