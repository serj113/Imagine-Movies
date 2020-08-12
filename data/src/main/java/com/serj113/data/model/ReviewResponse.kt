package com.serj113.data.model


import com.squareup.moshi.Json

data class ReviewResponse(
    @Json(name = "id")
    var id: Int,
    @Json(name = "page")
    var page: Int,
    @Json(name = "results")
    var results: List<RemoteReview>,
    @Json(name = "total_pages")
    var totalPages: Int,
    @Json(name = "total_results")
    var totalResults: Int
)