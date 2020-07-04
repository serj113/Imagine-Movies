package com.serj113.data.model

import com.serj113.domain.entity.Movie
import com.squareup.moshi.Json

data class Result(
    @field:Json(name = "adult")
    var adult: Boolean = false,
    @field:Json(name = "backdrop_path")
    var backdropPath: String? = "",
    @field:Json(name = "genre_ids")
    var genreIds: List<Int> = listOf(),
    @field:Json(name = "id")
    var id: Int = 0,
    @field:Json(name = "original_language")
    var originalLanguage: String = "",
    @field:Json(name = "original_title")
    var originalTitle: String = "",
    @field:Json(name = "overview")
    var overview: String = "",
    @field:Json(name = "popularity")
    var popularity: Double = 0.0,
    @field:Json(name = "poster_path")
    var posterPath: String? = "",
    @field:Json(name = "release_date")
    var releaseDate: String = "",
    @field:Json(name = "title")
    var title: String = "",
    @field:Json(name = "video")
    var video: Boolean = false,
    @field:Json(name = "vote_average")
    var voteAverage: Double = 0.0,
    @field:Json(name = "vote_count")
    var voteCount: Int = 0
)

fun Result.toMovieEntity() = Movie(
    adult,
    backdropPath ?: "",
    genreIds,
    id,
    originalLanguage,
    originalTitle,
    overview,
    popularity,
    posterPath ?: "",
    releaseDate,
    title,
    video,
    voteAverage,
    voteCount
)

fun List<Result>.toMovieEntities(): List<Movie> = this.map { it.toMovieEntity() }