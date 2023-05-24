package com.girrafeecstud.signals.rescuers_api.engine

import android.content.Context
import kotlinx.coroutines.flow.Flow

interface RescuersEngine {

    fun startRescuersEngine(context: Context)

    fun getState(): Flow<RescuersEngineState>
}