package com.serj113.data.model

import com.serj113.domain.entity.Crew
import com.squareup.moshi.Json

data class RemoteCrew(
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

fun RemoteCrew.toCrewEntity() = Crew(creditId, department, gender, id, job, name, profilePath ?: "")

fun List<RemoteCrew>.toCrewEntities(): List<Crew> = map { it.toCrewEntity() }