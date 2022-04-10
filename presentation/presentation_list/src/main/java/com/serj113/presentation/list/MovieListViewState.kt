package com.serj113.presentation.list

import com.serj113.model.Movie

sealed class MovieListViewState {
    data class Success(
        val data: List<Movie>,
        val popularMovies: List<Movie> = listOf()
    ) : MovieListViewState()
    object Loading : MovieListViewState()
    data class Error(var error: Throwable) : MovieListViewState()
}
