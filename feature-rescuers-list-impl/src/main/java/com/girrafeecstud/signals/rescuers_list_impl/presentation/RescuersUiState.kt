package com.girrafeecstud.signals.rescuers_list_impl.presentation

import com.girrafeecstud.signals.rescuers_api.domain.Rescuer

sealed class RescuersUiState {
    object RescuersLoading : RescuersUiState()
    object NoRescuers : RescuersUiState()
    class ShowRescuersList(val rescuers: List<Rescuer>) : RescuersUiState()
}