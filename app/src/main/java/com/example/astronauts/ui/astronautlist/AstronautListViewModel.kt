package com.example.astronauts.ui.astronautlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astronauts.datalayer.ErrorType
import com.example.astronauts.domainlayer.usecase.GetAstronautsListUseCase
import com.example.astronauts.domainlayer.usecase.GetSortedListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AstronautListViewModel @Inject constructor(
    private val getAstronautsListUseCase: GetAstronautsListUseCase,
    private val getSortedListUseCase: GetSortedListUseCase
) : ViewModel() {
    private val _uiState = MutableLiveData<AstronautsUiState>()
    val uiState: LiveData<AstronautsUiState> = _uiState
    private var isAscending = true
    init {
        initialise()
    }

    fun initialise() {
        _uiState.value = AstronautsUiState.Loading
        viewModelScope.launch {
            try {
                val result = getAstronautsListUseCase()
                when (result.error) {
                    ErrorType.None -> result.response?.let {
                        _uiState.postValue(AstronautsUiState.Success(it))
                    }
                    ErrorType.Network -> _uiState.postValue(AstronautsUiState.Error)
                }

            } catch (error: Error) {
                _uiState.postValue(AstronautsUiState.Error)
            }
        }
    }

    fun onSortClicked() {
        viewModelScope.launch {
            val astronauts = (uiState.value as? AstronautsUiState.Success)?.astronauts
            astronauts?.let {
                val result = getSortedListUseCase(it, isAscending)
                isAscending = !isAscending
                _uiState.postValue(AstronautsUiState.Success(result))
            }
        }
    }
}
