package com.example.astronauts.domainlayer.usecase

import com.example.astronauts.datalayer.ApiResult
import com.example.astronauts.datalayer.AstronautsRepository
import com.example.astronauts.datalayer.ErrorType
import com.example.astronauts.datalayer.models.Astronaut
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetAstronautsListUseCaseTest {
    @Mock
    lateinit var repository: AstronautsRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun givenCallToGetAstronautList_whenResultIsAvailable_thenReturnResults() = runTest{
        val mockAstronaut = Astronaut(
            id = 1,
            name = "Brad",
            nationality = "Aus",
            profileImage = "image"
        )
        val dispatcher = StandardTestDispatcher(testScheduler)
        val testee = GetAstronautsListUseCase(repository, dispatcher)
        Mockito.`when`(repository.getAstronauts())
            .thenReturn(ApiResult(response = listOf(mockAstronaut), ErrorType.None))

        val result = testee.invoke()

        advanceUntilIdle()

        assertNotNull(result)
        assertEquals(ErrorType.None, result.error)
        assertNotNull(result.response)
        assertEquals(1, result.response?.size)
        assertEquals(mockAstronaut, result.response?.get(0))
    }
}