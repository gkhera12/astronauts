package com.example.astronauts.domainlayer.usecase

import com.example.astronauts.datalayer.ApiResult
import com.example.astronauts.datalayer.AstronautsRepository
import com.example.astronauts.datalayer.models.Astronaut
import com.example.astronauts.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAstronautDetailUseCase @Inject constructor(
    private val astronautRepository: AstronautsRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(id: Int): ApiResult<Astronaut> {
        return withContext(dispatcher) {
            astronautRepository.getAstronautDetail(id)
        }
    }
}