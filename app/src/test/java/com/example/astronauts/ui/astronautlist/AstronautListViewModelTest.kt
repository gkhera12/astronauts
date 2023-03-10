package com.example.astronauts.ui.astronautlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.astronauts.datalayer.ApiResult
import com.example.astronauts.datalayer.AstronautsRepository
import com.example.astronauts.datalayer.ErrorType
import com.example.astronauts.datalayer.models.Astronaut
import com.example.astronauts.domainlayer.usecase.GetAstronautsListUseCase
import com.example.astronauts.domainlayer.usecase.GetSortedListUseCase
import com.example.astronauts.utils.MainCoroutineRule
import com.example.astronauts.utils.getOrAwaitValue
import junit.framework.TestCase.*
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class AstronautListViewModelTest() {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var repository: AstronautsRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun givenViewModelIsInitialised_whenGetAstronautListReturnsSuccess_thenVerifyAstronautsList() =
        runTest {
            // given
            val mockAstronaut = Astronaut(
                id = 1,
                name = "Brad",
                nationality = "Aus",
                profileImage = "image"
            )
            val dispatcher = StandardTestDispatcher(testScheduler)
            val getAstronautsListUseCase = GetAstronautsListUseCase(repository, dispatcher)
            val getSortedListUseCase = GetSortedListUseCase(dispatcher)
            Mockito.`when`(repository.getAstronauts())
                .thenReturn(ApiResult(response = listOf(mockAstronaut), ErrorType.None))

            // when
            val testee = AstronautListViewModel(getAstronautsListUseCase, getSortedListUseCase)

            advanceUntilIdle()

            // then
            val result = testee.uiState.getOrAwaitValue()
            assertNotNull(result)
            assertTrue(result is AstronautsUiState.Success)
            val astronauts = (result as AstronautsUiState.Success).astronauts
            assertEquals(1, astronauts.size)
            assertEquals(mockAstronaut, astronauts[0])
        }

    @Test
    fun givenViewModelIsInitialised_whenGetAstronautListReturnsError_thenVerifyError() =
        runTest {
            // given
            val dispatcher = StandardTestDispatcher(testScheduler)
            val getAstronautsListUseCase = GetAstronautsListUseCase(repository, dispatcher)
            val getSortedListUseCase = GetSortedListUseCase(dispatcher)
            Mockito.`when`(repository.getAstronauts())
                .thenReturn(ApiResult(response = emptyList(), ErrorType.Network))

            // when
            val testee = AstronautListViewModel(getAstronautsListUseCase, getSortedListUseCase)

            advanceUntilIdle()

            // then
            val result = testee.uiState.getOrAwaitValue()
            assertNotNull(result)
            assertTrue(result is AstronautsUiState.Error)
        }

    @Test
    fun givenViewModelIsInitialised_whenListIsSorted_thenVerifyAstronautsListIsSorted() =
        runTest {
            // given
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
            val dispatcher = StandardTestDispatcher(testScheduler)
            val getAstronautsListUseCase = GetAstronautsListUseCase(repository, dispatcher)
            val getSortedListUseCase = GetSortedListUseCase(dispatcher)
            Mockito.`when`(repository.getAstronauts())
                .thenReturn(ApiResult(response = listOf(mockAstronaut1, mockAstronaut2, mockAstronaut3, mockAstronaut4), ErrorType.None))
            val testee = AstronautListViewModel(getAstronautsListUseCase, getSortedListUseCase)
            advanceUntilIdle()

            // when
            testee.onSortClicked()

            // then
            advanceUntilIdle()
            val result = testee.uiState.getOrAwaitValue()
            assertNotNull(result)
            assertTrue(result is AstronautsUiState.Success)
            val astronauts = (result as AstronautsUiState.Success).astronauts
            assertEquals(4, astronauts.size)
            assertEquals(mockAstronaut2, astronauts[0])
            assertEquals(mockAstronaut4, astronauts[1])
            assertEquals(mockAstronaut3, astronauts[2])
            assertEquals(mockAstronaut1, astronauts[3])
        }
}