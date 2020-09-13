package com.serj113.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReviewList(
    @Json(name = "id")
    var id: Int,
    @Json(name = "page")
    var page: Int,
    @Json(name = "results")
    var results: List<Review>,
    @Json(name = "total_pages")
    var totalPages: Int,
    @Json(name = "total_results")
    var totalResults: Int
)