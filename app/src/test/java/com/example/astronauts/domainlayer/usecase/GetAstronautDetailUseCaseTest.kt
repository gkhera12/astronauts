package com.example.astronauts.domainlayer.usecase

import com.example.astronauts.datalayer.ApiResult
import com.example.astronauts.datalayer.AstronautsRepository
import com.example.astronauts.datalayer.ErrorType
import com.example.astronauts.datalayer.models.Astronaut
import junit.framework.TestCase
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetAstronautDetailUseCaseTest {
    @Mock
    lateinit var repository: AstronautsRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun givenCallToGetAstronautDetails_whenResultIsAvailable_thenReturnResults() = runTest{
        val mockAstronaut = Astronaut(
            id = 1,
            name = "Brad",
            nationality = "Aus",
            profileImage = "image"
        )
        val dispatcher = StandardTestDispatcher(testScheduler)
        val testee = GetAstronautDetailUseCase(repository, dispatcher)
        Mockito.`when`(repository.getAstronautDetail(1))
            .thenReturn(ApiResult(response = mockAstronaut, ErrorType.None))

        val result = testee.invoke(1)

        advanceUntilIdle()

        TestCase.assertNotNull(result)
        TestCase.assertEquals(ErrorType.None, result.error)
        TestCase.assertNotNull(result.response)
        TestCase.assertEquals(mockAstronaut, result.response)
    }
}