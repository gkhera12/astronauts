package com.example.astronauts.domainlayer.usecase

import com.example.astronauts.datalayer.models.Astronaut
import com.example.astronauts.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetSortedListUseCase @Inject constructor(
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(astronauts: List<Astronaut>, isAscendingOrder:Boolean): List<Astronaut> {
        return withContext(dispatcher) {
            if (isAscendingOrder) {
                astronauts.sortedBy { it.name }
            } else {
                astronauts.sortedByDescending { it.name }
            }
        }
    }
}