package com.girrafeecstud.sos_signal_impl.di.dependencies

import android.content.Context
import com.girrafeecstud.society_safety_app.event_bus.EventBus
import com.girrafeecstud.sos_signal_api.domain.SendSosSignalUseCase

interface SosSignalDependencies {

    fun getEventBus(): EventBus
}