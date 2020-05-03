package com.serj113.imaginemovies.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Result(
    @Json(name = "adult")
    var adult: Boolean = false,
    @Json(name = "backdrop_path")
    var backdropPath: String? = "",
    @Json(name = "genre_ids")
    var genreIds: List<Int> = listOf(),
    @Json(name = "id")
    var id: Int = 0,
    @Json(name = "original_language")
    var originalLanguage: String = "",
    @Json(name = "original_title")
    var originalTitle: String = "",
    @Json(name = "overview")
    var overview: String = "",
    @Json(name = "popularity")
    var popularity: Double = 0.0,
    @Json(name = "poster_path")
    var posterPath: String = "",
    @Json(name = "release_date")
    var releaseDate: String = "",
    @Json(name = "title")
    var title: String = "",
    @Json(name = "video")
    var video: Boolean = false,
    @Json(name = "vote_average")
    var voteAverage: Double = 0.0,
    @Json(name = "vote_count")
    var voteCount: Int = 0
)