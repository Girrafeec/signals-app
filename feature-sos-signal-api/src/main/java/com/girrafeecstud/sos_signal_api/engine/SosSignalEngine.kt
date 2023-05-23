package com.girrafeecstud.sos_signal_api.engine

import android.content.Context
import com.girrafeecstud.sos_signal_api.domain.entity.SosSignal
import kotlinx.coroutines.flow.Flow

interface SosSignalEngine {

    fun enableSosSignal(
        sosSignal: SosSignal
    )

    fun updateSosSignal(sosSignal: SosSignal)

    fun disableSosSignal()

    fun getSosSignalState(): Flow<SosSignalState>

}