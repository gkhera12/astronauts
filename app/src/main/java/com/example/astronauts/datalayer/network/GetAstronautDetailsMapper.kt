package com.example.astronauts.datalayer.network

import com.example.astronauts.datalayer.models.Astronaut

class GetAstronautDetailsMapper : Mapper<AstronautDto, Astronaut> {

    override fun map(input: AstronautDto?): Astronaut? {
        return input?.let {
            Astronaut(
                it.id,
                it.name,
                it.nationality,
                it.profileImage,
                it.bio,
                it.dateOfBirth)
        }
    }
}