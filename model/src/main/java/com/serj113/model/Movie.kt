package com.serj113.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Movie (
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
) : Parcelable