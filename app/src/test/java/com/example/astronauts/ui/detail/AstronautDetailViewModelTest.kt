package com.example.astronauts.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.example.astronauts.datalayer.ApiResult
import com.example.astronauts.datalayer.AstronautsRepository
import com.example.astronauts.datalayer.ErrorType
import com.example.astronauts.datalayer.models.Astronaut
import com.example.astronauts.domainlayer.usecase.GetAstronautDetailUseCase
import com.example.astronauts.utils.MainCoroutineRule
import com.example.astronauts.utils.getOrAwaitValue
import junit.framework.TestCase
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class AstronautDetailViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()
    private lateinit var testee: AstronautDetailViewModel

    @Mock
    lateinit var repository: AstronautsRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun givenViewModelIsInitialised_whenGetAstronautListReturnsSuccess_thenVerifyAstronautDetails() =
        runTest {
            // given
            val mockAstronaut = Astronaut(
                id = 1,
                name = "Brad",
                nationality = "Aus",
                profileImage = "image",
                bio = "MyBio",
                dateOfBirth = "12/12/1982"
            )
            val savedState = SavedStateHandle(mapOf("id" to 1))
            val dispatcher = StandardTestDispatcher(testScheduler)
            val getAstronautDetailUseCase = GetAstronautDetailUseCase(repository, dispatcher)
            Mockito.`when`(repository.getAstronautDetail(1))
                .thenReturn(ApiResult(response = mockAstronaut, ErrorType.None))

            // when
            val testee = AstronautDetailViewModel(savedState, getAstronautDetailUseCase)

            advanceUntilIdle()

            // then
            val result = testee.uiState.getOrAwaitValue()
            TestCase.assertNotNull(result)
            TestCase.assertTrue(result is AstronautDetailUiState.Success)
            val astronaut = (result as AstronautDetailUiState.Success).astronaut
            TestCase.assertEquals(mockAstronaut, astronaut)
        }

    @Test
    fun givenViewModelIsInitialised_whenGetAstronautListReturnsError_thenVerifyError() =
        runTest {
            // given
            val mockAstronaut = Astronaut(
                id = 1,
                name = "Brad",
                nationality = "Aus",
                profileImage = "image",
                bio = "MyBio",
                dateOfBirth = "12/12/1982"
            )
            val savedState = SavedStateHandle(mapOf("id" to 1))
            val dispatcher = StandardTestDispatcher(testScheduler)
            val getAstronautDetailUseCase = GetAstronautDetailUseCase(repository, dispatcher)
            Mockito.`when`(repository.getAstronautDetail(1))
                .thenReturn(ApiResult(response = mockAstronaut, ErrorType.Network))

            // when
            val testee = AstronautDetailViewModel(savedState, getAstronautDetailUseCase)

            advanceUntilIdle()

            // then
            val result = testee.uiState.getOrAwaitValue()
            TestCase.assertNotNull(result)
            TestCase.assertTrue(result is AstronautDetailUiState.Error)
        }

}