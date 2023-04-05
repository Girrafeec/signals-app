package com.girrafeecstud.sos_signal_impl.di.dependencies

import com.girrafeecstud.countdown_timer_api.engine.BaseCountDownTimerEngine
import com.girrafeecstud.signals.event_bus.EventBus

interface SosSignalDependencies {

    fun getEventBus(): EventBus

    fun getCountDownTimerEngine(): BaseCountDownTimerEngine
}