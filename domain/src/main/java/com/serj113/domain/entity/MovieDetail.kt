package com.serj113.domain.entity

data class MovieDetail(
    var adult: Boolean,
    var backdropPath: String,
    var budget: Long,
    var credits: Credits,
    var genres: List<Genre>,
    var homepage: String,
    var id: Int,
    var imdbId: String,
    var originalLanguage: String,
    var originalTitle: String,
    var overview: String,
    var popularity: Double,
    var posterPath: String,
    var releaseDate: String,
    var revenue: Long,
    var runtime: Int,
    var status: String,
    var tagline: String,
    var title: String,
    var video: Boolean,
    var voteAverage: Double,
    var voteCount: Int
)