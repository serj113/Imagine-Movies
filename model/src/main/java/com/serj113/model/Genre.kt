package com.serj113.model

import com.squareup.moshi.Json

data class Genre(
    @field:Json(name = "id")
    var id: Int,
    @field:Json(name = "name")
    var name: String
)