package com.girrafeecstud.signals.signals_api.engine

import android.content.Context

interface SignalsEngine {

    fun startSignalsEngine(context: Context)

    fun updateSignalDetails(context: Context, signalId: String)

}