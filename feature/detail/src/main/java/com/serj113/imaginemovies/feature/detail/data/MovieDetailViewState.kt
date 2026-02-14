package com.serj113.imaginemovies.feature.detail.data

data class MovieDetailViewState(
    val backdropUrl: String = "",
    val synopsis: String = "",
    val voteAverage: String = "",
    val voteCount: String = "",
    val originalTitle: String = "",
    val releaseDate: String = "",
    val status: String = "",
    val budget: String = "",
    val revenue: String = "",
    val casts: List<Cast> = listOf(),
    val reviews: List<Review> = listOf(),
    val recommendations: List<Movie> = listOf(),
    val similar: List<Movie> = listOf(),
) {
    data class Cast(
        val character: String = "",
        val name: String = "",
        val profilePath: String = "",
    )

    data class Movie(
        val title: String = "",
        val rating: String = "",
        val posterUrl: String = "",
    )

    data class Review(
        val author: String = "",
        val content: String = "",
        val reviewUrl: String = "",
    )
}
