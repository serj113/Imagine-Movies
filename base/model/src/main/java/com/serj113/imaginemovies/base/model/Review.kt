package com.serj113.imaginemovies.base.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Review (
    @field:Json(name = "author")
    var author: String = "",
    @field:Json(name = "content")
    var content: String ="",
    @field:Json(name = "id")
    var id: String = "",
    @field:Json(name = "url")
    var url: String = ""
)