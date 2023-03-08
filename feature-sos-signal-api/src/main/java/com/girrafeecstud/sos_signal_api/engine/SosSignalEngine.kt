package com.girrafeecstud.sos_signal_api.engine

import android.content.Context
import com.girrafeecstud.sos_signal_api.domain.entity.SosSignal
import kotlinx.coroutines.flow.Flow

interface SosSignalEngine {

    fun enableSosSignal(
        context: Context,
        sosSignal: SosSignal
    )

    fun updateSosSignal(context: Context, sosSignal: SosSignal)

    fun disableSosSignal(context: Context)

    suspend fun getSosSignalState(): Flow<SosSignalState>

}