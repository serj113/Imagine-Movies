package com.serj113.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductionCountry(
    @field:Json(name = "iso_3166_1")
    var iso31661: String,
    @field:Json(name = "name")
    var name: String
)