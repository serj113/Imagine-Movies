package com.serj113.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Genre(
    @field:Json(name = "id")
    var id: Int,
    @field:Json(name = "name")
    var name: String
)