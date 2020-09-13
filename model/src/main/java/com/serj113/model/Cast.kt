package com.serj113.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Cast(
    @field:Json(name = "cast_id")
    var castId: Int = 0,
    @field:Json(name = "character")
    var character: String = "",
    @field:Json(name = "credit_id")
    var creditId: String = "",
    @field:Json(name = "gender")
    var gender: Int = 0,
    @field:Json(name = "id")
    var id: Int = 0,
    @field:Json(name = "name")
    var name: String = "",
    @field:Json(name = "order")
    var order: Int = 0,
    @field:Json(name = "profile_path")
    var profilePath: String? = null
)