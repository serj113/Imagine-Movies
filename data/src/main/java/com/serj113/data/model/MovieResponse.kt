package com.serj113.data.model

import com.squareup.moshi.Json

data class MovieResponse (
    @field:Json(name = "page")
    var page: Int = 0,
    @field:Json(name = "results")
    var results: List<RemoteMovie> = listOf(),
    @field:Json(name = "total_pages")
    var totalPages: Int = 0,
    @field:Json(name = "total_results")
    var totalResults: Int = 0
)