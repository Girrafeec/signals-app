package com.girrafeecstud.sos_signal_api.engine

import android.content.Context

interface SosSignalEngine {

    fun startSosSignal(context: Context)

    fun disableSosSignal()

}