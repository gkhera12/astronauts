package com.example.astronauts.domainlayer.usecase

import com.example.astronauts.datalayer.models.Astronaut
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetSortedListUseCaseTest {

    @Test
    fun givenAstronautList_whenSortedInAscending_thenReturnResults() = runTest {

        val mockAstronaut1 = Astronaut(
            id = 1,
            name = "Rad",
            nationality = "Aus",
            profileImage = "image"
        )
        val mockAstronaut2 = Astronaut(
            id = 2,
            name = "Crad",
            nationality = "Aus",
            profileImage = "image"
        )
        val mockAstronaut3 = Astronaut(
            id = 3,
            name = "Nrad",
            nationality = "Aus",
            profileImage = "image"
        )
        val mockAstronaut4 = Astronaut(
            id = 4,
            name = "Mrad",
            nationality = "Aus",
            profileImage = "image"
        )
        val mockList = listOf(mockAstronaut1, mockAstronaut2, mockAstronaut3, mockAstronaut4)
        val dispatcher = StandardTestDispatcher(testScheduler)
        val testee = GetSortedListUseCase(dispatcher)

        val result = testee.invoke(mockList, true)

        advanceUntilIdle()

        assertNotNull(result)
        assertEquals(4, result.size)
        assertEquals(mockAstronaut2, result[0])
        assertEquals(mockAstronaut4, result[1])
        assertEquals(mockAstronaut3, result[2])
        assertEquals(mockAstronaut1, result[3])
    }

    @Test
    fun givenAstronautList_whenSortedInDescending_thenReturnResults() = runTest {

        val mockAstronaut1 = Astronaut(
            id = 1,
            name = "Rad",
            nationality = "Aus",
            profileImage = "image"
        )
        val mockAstronaut2 = Astronaut(
            id = 2,
            name = "Crad",
            nationality = "Aus",
            profileImage = "image"
        )
        val mockAstronaut3 = Astronaut(
            id = 3,
            name = "Nrad",
            nationality = "Aus",
            profileImage = "image"
        )
        val mockAstronaut4 = Astronaut(
            id = 4,
            name = "Mrad",
            nationality = "Aus",
            profileImage = "image"
        )
        val mockList = listOf(mockAstronaut1, mockAstronaut2, mockAstronaut3, mockAstronaut4)
        val dispatcher = StandardTestDispatcher(testScheduler)
        val testee = GetSortedListUseCase(dispatcher)

        val result = testee.invoke(mockList, false)

        advanceUntilIdle()

        assertNotNull(result)
        assertEquals(4, result.size)
        assertEquals(mockAstronaut1, result[0])
        assertEquals(mockAstronaut3, result[1])
        assertEquals(mockAstronaut4, result[2])
        assertEquals(mockAstronaut2, result[3])
    }
}