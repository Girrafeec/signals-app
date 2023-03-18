package com.girrafeecstud.signals.rescuers_list_api.presenation

import com.girrafeecstud.signals.rescuers_api.domain.Rescuer
import com.girrafeecstud.signals.core_base.presentation.base.UiState

sealed class RescuersListSharedState : UiState {
    object CloseRescuerDetails : RescuersListSharedState()
    class ShowRescuerDetails(val rescuer: Rescuer) : RescuersListSharedState()
}
