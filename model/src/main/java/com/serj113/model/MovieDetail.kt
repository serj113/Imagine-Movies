package com.serj113.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDetail(
    @field:Json(name = "adult")
    var adult: Boolean = false,
    @field:Json(name = "backdrop_path")
    var backdropPath: String = "",
    @field:Json(name = "belongs_to_collection")
    var belongsToCollection: Any? = null,
    @field:Json(name = "budget")
    var budget: Long = 0L,
    @field:Json(name = "credits")
    var credits: Credits = Credits(),
    @field:Json(name = "genres")
    var genres: List<Genre> = listOf(),
    @field:Json(name = "homepage")
    var homepage: String = "",
    @field:Json(name = "id")
    var id: Int = 0,
    @field:Json(name = "imdb_id")
    var imdbId: String = "",
    @field:Json(name = "original_language")
    var originalLanguage: String = "",
    @field:Json(name = "original_title")
    var originalTitle: String = "",
    @field:Json(name = "overview")
    var overview: String = "",
    @field:Json(name = "popularity")
    var popularity: Double = 0.0,
    @field:Json(name = "poster_path")
    var posterPath: String = "",
    @field:Json(name = "production_companies")
    var productionCompanies: List<ProductionCompany> = listOf(),
    @field:Json(name = "production_countries")
    var productionCountries: List<ProductionCountry> = listOf(),
    @field:Json(name = "release_date")
    var releaseDate: String = "",
    @field:Json(name = "revenue")
    var revenue: Long = 0L,
    @field:Json(name = "runtime")
    var runtime: Int = 0,
    @field:Json(name = "spoken_languages")
    var spokenLanguages: List<SpokenLanguage> = listOf(),
    @field:Json(name = "status")
    var status: String = "",
    @field:Json(name = "tagline")
    var tagline: String = "",
    @field:Json(name = "title")
    var title: String = "",
    @field:Json(name = "video")
    var video: Boolean = false,
    @field:Json(name = "vote_average")
    var voteAverage: Double = 0.0,
    @field:Json(name = "vote_count")
    var voteCount: Int = 0
)