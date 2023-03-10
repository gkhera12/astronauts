package com.example.astronauts.datalayer.network

import com.example.astronauts.datalayer.ApiResult
import com.example.astronauts.datalayer.models.Astronaut
import javax.inject.Inject

interface RemoteDataService {
    fun getAstronauts(): ApiResult<List<Astronaut>>
    fun getAstronautDetail(id: Int): ApiResult<Astronaut>
}

class RemoteDataServiceImpl @Inject constructor(private val spaceLaunchService: SpaceLaunchService) :
    RemoteDataService {

    override fun getAstronauts(): ApiResult<List<Astronaut>> {
        val call = spaceLaunchService.getAstronauts()
        return RetrofitCallExecuter<AstronautsDto, List<Astronaut>>()
            .execute(
                call,
                GetAstronautsMapper()
            )
    }

    override fun getAstronautDetail(id: Int): ApiResult<Astronaut> {
        val call = spaceLaunchService.getAstronautDetail(id)
        return RetrofitCallExecuter<AstronautDto, Astronaut>()
            .execute(
                call,
                GetAstronautDetailsMapper()
            )
    }
}