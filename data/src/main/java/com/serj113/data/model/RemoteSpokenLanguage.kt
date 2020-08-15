package com.serj113.data.model

import com.squareup.moshi.Json

data class RemoteSpokenLanguage(
    @field:Json(name = "iso_639_1")
    var iso6391: String,
    @field:Json(name = "name")
    var name: String
)