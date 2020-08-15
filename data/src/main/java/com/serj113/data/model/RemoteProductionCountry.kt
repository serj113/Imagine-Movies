package com.serj113.data.model

import com.squareup.moshi.Json

data class RemoteProductionCountry(
    @field:Json(name = "iso_3166_1")
    var iso31661: String,
    @field:Json(name = "name")
    var name: String
)