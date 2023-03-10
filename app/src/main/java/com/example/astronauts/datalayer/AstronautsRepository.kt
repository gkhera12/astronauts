package com.example.astronauts.datalayer

import com.example.astronauts.datalayer.models.Astronaut
import com.example.astronauts.datalayer.network.RemoteDataService
import javax.inject.Inject
import javax.inject.Singleton

interface AstronautsRepository {
    fun getAstronauts(): ApiResult<List<Astronaut>>
    fun getAstronautDetail(id: Int): ApiResult<Astronaut>
}

@Singleton
class AstronautsRepositoryImpl @Inject constructor(
    private val remoteDataService: RemoteDataService
) : AstronautsRepository {

    override fun getAstronauts(): ApiResult<List<Astronaut>> {
        return remoteDataService.getAstronauts()
    }

    override fun getAstronautDetail(id: Int): ApiResult<Astronaut> {
        return remoteDataService.getAstronautDetail(id)
    }
}