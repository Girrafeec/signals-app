package com.girrafeecstud.signals.rescuers_list_api.presenation

import com.girrafeecstud.signals.rescuers_api.domain.Rescuer
import com.girrafeecstud.core_ui.presentation.UiState

sealed class RescuersListSharedState : com.girrafeecstud.core_ui.presentation.UiState {
    object CloseRescuerDetails : RescuersListSharedState()
    class ShowRescuerDetails(val rescuer: Rescuer) : RescuersListSharedState()
}
