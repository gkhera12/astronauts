package com.example.astronauts.datalayer.models

data class Astronaut(
    val id: Int,
    val name: String,
    val nationality: String,
    val profileImage: String,
    val bio: String? = null,
    val dateOfBirth: String? = null
)