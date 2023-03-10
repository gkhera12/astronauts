package com.example.astronauts.datalayer.network

import com.example.astronauts.datalayer.models.Astronaut
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AstronautsDto(
    @field:Json(name = "results")
    val astronauts: List<AstronautDto>? = null
)

@JsonClass(generateAdapter = true)
data class AstronautDto(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "nationality")
    val nationality: String,
    @field:Json(name = "profile_image_thumbnail")
    val profileImage: String,
    @field:Json(name = "bio")
    val bio: String? = null,
    @field:Json(name = "date_of_birth")
    val dateOfBirth: String? = null
)


fun AstronautsDto.asDomainModel(): List<Astronaut> {
    return astronauts?.map {
        Astronaut(
            it.id,
            it.name,
            it.nationality,
            it.profileImage
        )
    } ?: listOf()
}