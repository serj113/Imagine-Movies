package com.serj113.imaginemovies.base.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Crew(
    @field:Json(name = "credit_id")
    var creditId: String = "",
    @field:Json(name = "department")
    var department: String = "",
    @field:Json(name = "gender")
    var gender: Int = 0,
    @field:Json(name = "id")
    var id: Int = 0,
    @field:Json(name = "job")
    var job: String = "",
    @field:Json(name = "name")
    var name: String = "",
    @field:Json(name = "profile_path")
    var profilePath: String? = null
)