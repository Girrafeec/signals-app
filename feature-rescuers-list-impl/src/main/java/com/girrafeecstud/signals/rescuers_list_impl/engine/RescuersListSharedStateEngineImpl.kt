package com.girrafeecstud.signals.rescuers_list_impl.engine

import com.girrafeecstud.signals.rescuers_api.domain.Rescuer
import com.girrafeecstud.signals.rescuers_list_api.presenation.RescuersListSharedState
import com.girrafeecstud.signals.rescuers_list_api.presenation.RescuersListSharedStateEngine
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class RescuersListSharedStateEngineImpl @Inject constructor() : RescuersListSharedStateEngine {

    private var _state = MutableStateFlow<RescuersListSharedState>(RescuersListSharedState.CloseRescuerDetails)

    override fun showRescuerDetails(rescuer: Rescuer) {
        _state.update { RescuersListSharedState.ShowRescuerDetails(rescuer = rescuer) }
    }

    override fun closeRescuerDetails() {
        _state.update { RescuersListSharedState.CloseRescuerDetails }
    }

    override fun getState(): Flow<RescuersListSharedState> =
        _state.asStateFlow()
}