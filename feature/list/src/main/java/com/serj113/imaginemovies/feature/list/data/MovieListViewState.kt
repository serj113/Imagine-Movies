package com.serj113.imaginemovies.feature.list.data

data class MovieListViewState(
    val movies: List<MovieViewState> = listOf(),
    val popularMovies: List<MovieViewState> = listOf(),
    val isLoading: Boolean = false
) {
    data class MovieViewState(
        val title: String = "",
        val rating: String = "",
        val posterUrl: String = "",
    )
}
