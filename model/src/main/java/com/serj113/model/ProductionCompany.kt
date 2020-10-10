package com.serj113.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductionCompany(
    @field:Json(name = "id")
    var id: Int,
    @field:Json(name = "logo_path")
    var logoPath: String? = null,
    @field:Json(name = "name")
    var name: String,
    @field:Json(name = "origin_country")
    var originCountry: String
)