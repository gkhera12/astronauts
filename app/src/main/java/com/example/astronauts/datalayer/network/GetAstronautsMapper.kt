package com.example.astronauts.datalayer.network

import com.example.astronauts.datalayer.models.Astronaut

class GetAstronautsMapper : Mapper<AstronautsDto, List<Astronaut>> {

    override fun map(input: AstronautsDto?): List<Astronaut> {
        return input?.asDomainModel() ?: listOf()
    }
}