package com.serj113.imaginemovies.base.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SpokenLanguage(
    @field:Json(name = "iso_639_1")
    var iso6391: String,
    @field:Json(name = "name")
    var name: String
)