package com.example.astronauts.ui.detail

import com.example.astronauts.datalayer.models.Astronaut

sealed class AstronautDetailUiState {
    object Loading : AstronautDetailUiState()
    object Error: AstronautDetailUiState()
    data class Success(val astronaut: Astronaut): AstronautDetailUiState()
}
