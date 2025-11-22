package com.serj113.imaginemovies.feature.list

import com.serj113.imaginemovies.base.model.Movie

sealed class MovieListViewState {
    data class Success(
        val data: List<Movie>,
        val popularMovies: List<Movie> = listOf()
    ) : MovieListViewState()
    object Loading : MovieListViewState()
    data class Error(var error: Throwable) : MovieListViewState()
}
