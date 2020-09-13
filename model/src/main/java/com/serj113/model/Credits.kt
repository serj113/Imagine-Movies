package com.serj113.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Credits(
    @field:Json(name = "cast")
    var cast: List<Cast> = listOf(),
    @field:Json(name = "crew")
    var crew: List<Crew> = listOf()
)