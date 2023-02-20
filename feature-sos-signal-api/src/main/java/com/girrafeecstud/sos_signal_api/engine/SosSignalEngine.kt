package com.girrafeecstud.sos_signal_api.engine

import android.content.Context
import com.girrafeecstud.sos_signal_api.domain.entity.SosSignal

interface SosSignalEngine {

    fun startSosSignal(context: Context, sosSignal: SosSignal)

    fun disableSosSignal(context: Context)

}