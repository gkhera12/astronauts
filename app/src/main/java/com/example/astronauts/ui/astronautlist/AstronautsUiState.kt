package com.example.astronauts.ui.astronautlist

import com.example.astronauts.datalayer.models.Astronaut

sealed class AstronautsUiState {
    object Loading : AstronautsUiState()
    object Error: AstronautsUiState()
    data class Success(val astronauts: List<Astronaut>): AstronautsUiState()
}
