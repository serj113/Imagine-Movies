package com.serj113.imaginemovies.data.model

import com.squareup.moshi.Json

data class Response(
    @field:Json(name = "page")
    var page: Int = 0,
    @field:Json(name = "results")
    var results: List<Result> = listOf(),
    @field:Json(name = "total_pages")
    var totalPages: Int = 0,
    @field:Json(name = "total_results")
    var totalResults: Int = 0
)