package com.serj113.imaginemovies.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class Response(
    var page: Int = 0,
    var results: List<Result> = listOf(),
    var totalPages: Int = 0,
    var totalResults: Int = 0
)