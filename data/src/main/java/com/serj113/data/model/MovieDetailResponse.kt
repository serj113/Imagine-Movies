package com.serj113.data.model

import com.serj113.domain.entity.MovieDetail
import com.squareup.moshi.Json

data class MovieDetailResponse(
    @field:Json(name = "adult")
    var adult: Boolean = false,
    @field:Json(name = "backdrop_path")
    var backdropPath: String = "",
    @field:Json(name = "belongs_to_collection")
    var belongsToCollection: Any? = null,
    @field:Json(name = "budget")
    var budget: Int = 0,
    @field:Json(name = "credits")
    var credits: RemoteCredits = RemoteCredits(),
    @field:Json(name = "genres")
    var genres: List<RemoteGenre> = listOf(),
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
    var productionCompanies: List<RemoteProductionCompany> = listOf(),
    @field:Json(name = "production_countries")
    var productionCountries: List<RemoteProductionCountry> = listOf(),
    @field:Json(name = "release_date")
    var releaseDate: String = "",
    @field:Json(name = "revenue")
    var revenue: Int = 0,
    @field:Json(name = "runtime")
    var runtime: Int = 0,
    @field:Json(name = "spoken_languages")
    var spokenLanguages: List<RemoteSpokenLanguage> = listOf(),
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

fun MovieDetailResponse.toMovieDetailEntity(): MovieDetail {
    return MovieDetail(
        adult,
        backdropPath ?: "",
        budget,
        credits.toCreditsEntity(),
        genres.toGenreEntities(),
        homepage,
        id,
        imdbId,
        originalLanguage,
        originalTitle,
        overview,
        popularity,
        posterPath,
        releaseDate,
        revenue,
        runtime,
        status,
        tagline,
        title,
        video,
        voteAverage,
        voteCount
    )
}