package com.girrafeecstud.signals.rescuers_list_api.presenation

import com.girrafeecstud.signals.rescuers_api.domain.Rescuer
import kotlinx.coroutines.flow.Flow

interface RescuersListSharedStateEngine {

    fun getState(): Flow<RescuersListSharedState>

    fun showRescuerDetails(rescuer: Rescuer)

    fun closeRescuerDetails()

}