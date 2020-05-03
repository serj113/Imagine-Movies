package com.serj113.imaginemovies.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Response(
    @Json(name = "page")
    var page: Int = 0,
    @Json(name = "results")
    var results: List<Result> = listOf(),
    @Json(name = "total_pages")
    var totalPages: Int = 0,
    @Json(name = "total_results")
    var totalResults: Int = 0
)