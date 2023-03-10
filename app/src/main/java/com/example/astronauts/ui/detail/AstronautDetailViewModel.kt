package com.example.astronauts.ui.detail

import androidx.lifecycle.*
import com.example.astronauts.datalayer.ErrorType
import com.example.astronauts.domainlayer.usecase.GetAstronautDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AstronautDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getAstronautDetailUseCase: GetAstronautDetailUseCase,
) : ViewModel() {
    private val id: Int = checkNotNull(savedStateHandle["id"])

    private val _uiState = MutableLiveData<AstronautDetailUiState>()
    val uiState: LiveData<AstronautDetailUiState> = _uiState

    init {
        initialise()
    }

    fun initialise() {
        _uiState.value = AstronautDetailUiState.Loading
        viewModelScope.launch {
            try {
                val result = getAstronautDetailUseCase(id)
                when (result.error) {
                    ErrorType.None -> result.response?.let {
                        _uiState.postValue(AstronautDetailUiState.Success(it))
                    }
                    ErrorType.Network -> _uiState.postValue(AstronautDetailUiState.Error)
                }

            } catch (error: Error) {
                _uiState.postValue(AstronautDetailUiState.Error)
            }
        }
    }
}
