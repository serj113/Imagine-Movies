package com.serj113.imaginemovies.data.repository

import androidx.paging.DataSource
import com.serj113.imaginemovies.data.api.MovieApi
import com.serj113.imaginemovies.domain.entity.Movie
import com.serj113.imaginemovies.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val movieApi: MovieApi) : MovieRepository {
    override fun fetchMovies(): DataSource<Int, Movie> {
    }
}