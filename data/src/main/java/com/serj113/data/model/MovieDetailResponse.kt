package com.serj113.data.model

import com.serj113.domain.entity.MovieDetail
import com.squareup.moshi.Json

data class MovieDetailResponse(
    @field:Json(name = "adult")
    var adult: Boolean,
    @field:Json(name = "backdrop_path")
    var backdropPath: String,
    @field:Json(name = "belongs_to_collection")
    var belongsToCollection: Any,
    @field:Json(name = "budget")
    var budget: Int,
    @field:Json(name = "credits")
    var credits: RemoteCredits,
    @field:Json(name = "genres")
    var genres: List<RemoteGenre>,
    @field:Json(name = "homepage")
    var homepage: String,
    @field:Json(name = "id")
    var id: Int,
    @field:Json(name = "imdb_id")
    var imdbId: String,
    @field:Json(name = "original_language")
    var originalLanguage: String,
    @field:Json(name = "original_title")
    var originalTitle: String,
    @field:Json(name = "overview")
    var overview: String,
    @field:Json(name = "popularity")
    var popularity: Double,
    @field:Json(name = "poster_path")
    var posterPath: String,
    @field:Json(name = "production_companies")
    var productionCompanies: List<RemoteProductionCompany>,
    @field:Json(name = "production_countries")
    var productionCountries: List<RemoteProductionCountry>,
    @field:Json(name = "release_date")
    var releaseDate: String,
    @field:Json(name = "revenue")
    var revenue: Int,
    @field:Json(name = "runtime")
    var runtime: Int,
    @field:Json(name = "spoken_languages")
    var spokenLanguages: List<RemoteSpokenLanguage>,
    @field:Json(name = "status")
    var status: String,
    @field:Json(name = "tagline")
    var tagline: String,
    @field:Json(name = "title")
    var title: String,
    @field:Json(name = "video")
    var video: Boolean,
    @field:Json(name = "vote_average")
    var voteAverage: Double,
    @field:Json(name = "vote_count")
    var voteCount: Int
)

fun MovieDetailResponse.toMovieDetailEntity() =
    MovieDetail(
        adult,
        backdropPath,
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