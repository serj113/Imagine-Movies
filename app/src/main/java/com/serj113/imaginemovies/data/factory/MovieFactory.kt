package com.serj113.imaginemovies.data.factory

import androidx.paging.DataSource
import com.serj113.imaginemovies.data.api.MovieApi
import com.serj113.imaginemovies.data.datasource.PageKeyedMovieDataSource
import com.serj113.imaginemovies.domain.entity.Movie
import javax.inject.Inject

class MovieFactory @Inject constructor(private val movieApi: MovieApi) : DataSource.Factory<Int, Movie>() {
    override fun create(): DataSource<Int, Movie> {
        return PageKeyedMovieDataSource()
    }
}