package com.serj113.imaginemovies.data.model


import com.serj113.imaginemovies.domain.entity.Movie
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class Result(
    var adult: Boolean = false,
    var backdropPath: String? = "",
    var genreIds: List<Int> = listOf(),
    var id: Int = 0,
    var originalLanguage: String = "",
    var originalTitle: String = "",
    var overview: String = "",
    var popularity: Double = 0.0,
    var posterPath: String = "",
    var releaseDate: String = "",
    var title: String = "",
    var video: Boolean = false,
    var voteAverage: Double = 0.0,
    var voteCount: Int = 0
)

fun Result.toMovieEntity() = Movie(
    adult,
    backdropPath,
    genreIds,
    id,
    originalLanguage,
    originalTitle,
    overview,
    popularity,
    posterPath,
    releaseDate,
    title,
    video,
    voteAverage,
    voteCount
)

fun List<Result>.toMovieEntities(): List<Movie> = this.map { it.toMovieEntity() }