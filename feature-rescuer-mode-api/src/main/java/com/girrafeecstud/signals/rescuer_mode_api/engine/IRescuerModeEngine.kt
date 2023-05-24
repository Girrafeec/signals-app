/* Created by Girrafeec */

package com.girrafeecstud.signals.rescuer_mode_api.engine

import kotlinx.coroutines.flow.Flow

interface IRescuerModeEngine {

    fun acceptSosSignal(signalId: String)

    fun rejectSosSignal()

    fun getRescuerModeState(): Flow<RescuerModeState>

}