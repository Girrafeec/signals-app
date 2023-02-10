package com.girrafeecstud.sos_signal_api.di

import com.girrafeecstud.sos_signal_api.domain.DisableSosSignalUseCase
import com.girrafeecstud.sos_signal_api.domain.SendSosSignalUseCase
import com.girrafeecstud.sos_signal_api.domain.UpdateSosSignalUseCase
import com.girrafeecstud.sos_signal_api.engine.SosSignalEngine

interface SosSignalFeatureApi {

    fun getSendSosSignalUseCase(): SendSosSignalUseCase

    fun getUpdateSosSignalUseCase(): UpdateSosSignalUseCase

    fun getDisableSosSignalUseCase(): DisableSosSignalUseCase

    fun getSosSignalEngine(): SosSignalEngine
}