package com.serj113.imaginemovies.domain.repository

import androidx.paging.DataSource
import com.serj113.imaginemovies.domain.entity.Movie

interface MovieRepository {
    fun fetchMovies(): DataSource<Int, Movie>
}