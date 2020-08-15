package com.serj113.data.model

import com.serj113.domain.entity.Credits
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class RemoteCredits(
    @field:Json(name = "cast")
    var cast: List<RemoteCast> = listOf(),
    @field:Json(name = "crew")
    var crew: List<RemoteCrew> = listOf()
)

fun RemoteCredits.toCreditsEntity(): Credits {
    return Credits(cast.toCastEntities(), crew.toCrewEntities())
}