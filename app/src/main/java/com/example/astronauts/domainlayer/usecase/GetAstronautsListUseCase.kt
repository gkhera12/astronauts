package com.example.astronauts.domainlayer.usecase

import com.example.astronauts.datalayer.AstronautsRepository
import com.example.astronauts.datalayer.models.Astronaut
import com.example.astronauts.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.example.astronauts.datalayer.ApiResult as ApiResult1

class GetAstronautsListUseCase @Inject constructor(
    private val astronautsRepository: AstronautsRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(): ApiResult1<List<Astronaut>> {
        return withContext(dispatcher) {
            astronautsRepository.getAstronauts()
        }
    }
}