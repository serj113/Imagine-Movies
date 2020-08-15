package com.serj113.data.model

import com.serj113.domain.entity.Cast
import com.squareup.moshi.Json

data class RemoteCast(
    @field:Json(name = "cast_id")
    var castId: Int,
    @field:Json(name = "character")
    var character: String,
    @field:Json(name = "credit_id")
    var creditId: String,
    @field:Json(name = "gender")
    var gender: Int,
    @field:Json(name = "id")
    var id: Int,
    @field:Json(name = "name")
    var name: String,
    @field:Json(name = "order")
    var order: Int,
    @field:Json(name = "profile_path")
    var profilePath: String
)

fun RemoteCast.toCastEntity() =
    Cast(castId, character, creditId, gender, id, name, order, profilePath)

fun List<RemoteCast>.toCastEntities() = map { it.toCastEntity() }