package com.serj113.data.model

import com.serj113.domain.entity.Credits
import com.squareup.moshi.Json

data class RemoteCredits(
    @field:Json(name = "cast")
    var cast: List<RemoteCast>,
    @field:Json(name = "crew")
    var crew: List<RemoteCrew>
)

fun RemoteCredits.toCreditsEntity = Credits(cast.toCastEntities(), crew.toCrewEntities())