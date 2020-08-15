package com.serj113.data.model

import com.serj113.domain.entity.Genre
import com.squareup.moshi.Json

data class RemoteGenre(
    @field:Json(name = "id")
    var id: Int,
    @field:Json(name = "name")
    var name: String
)

fun RemoteGenre.toGenreEntity() = Genre(id, name)

fun List<RemoteGenre>.toGenreEntities() = map { it.toGenreEntity() }