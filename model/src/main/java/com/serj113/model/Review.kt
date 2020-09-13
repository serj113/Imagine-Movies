package com.serj113.model

import com.squareup.moshi.Json

data class Review (
    @field:Json(name = "author")
    var author: String,
    @field:Json(name = "content")
    var content: String,
    @field:Json(name = "id")
    var id: String,
    @field:Json(name = "url")
    var url: String
)