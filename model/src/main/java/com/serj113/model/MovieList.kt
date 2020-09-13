package com.serj113.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieList (
    @field:Json(name = "page")
    var page: Int = 0,
    @field:Json(name = "results")
    var results: List<Movie> = listOf(),
    @field:Json(name = "total_pages")
    var totalPages: Int = 0,
    @field:Json(name = "total_results")
    var totalResults: Int = 0
)