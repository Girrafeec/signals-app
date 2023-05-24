package com.girrafeecstud.signals.signals_api.engine

import android.content.Context
import kotlinx.coroutines.flow.Flow

interface SignalsEngine {

    fun startSignalsEngine()

    fun getSignalDetails(signalId: String)

    fun getState(): Flow<SignalsEngineState>

}