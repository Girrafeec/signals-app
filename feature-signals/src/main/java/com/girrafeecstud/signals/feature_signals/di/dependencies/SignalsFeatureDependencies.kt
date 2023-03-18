package com.girrafeecstud.signals.feature_signals.di.dependencies

import com.girrafeecstud.signals.event_bus.EventBus
import com.girrafeecstud.sos_signal_api.domain.SendSosSignalUseCase
import com.girrafeecstud.sos_signal_api.engine.SosSignalEngine

interface SignalsFeatureDependencies {

    fun getSendSosSignalUseCase(): SendSosSignalUseCase

    fun getSosSignalEngine(): SosSignalEngine

    fun getEventBus(): EventBus
}