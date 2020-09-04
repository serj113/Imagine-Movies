package com.serj113.domain.entity

data class MovieReview(
    var id: Int,
    var page: Int,
    var results: List<Review>,
    var totalPages: Int,
    var totalResults: Int
)