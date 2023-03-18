package com.girrafeecstud.signals.event_bus

import com.girrafeecstud.sos_signal_api.domain.entity.SosSignal

sealed class AppEvent {
    object SendingSosSignal : AppEvent()
    object SendingSosSignalSuccessEvent : AppEvent()
    data class SendingSosSignalErrorEvent(
        val sosSignal: SosSignal
    ) : AppEvent()
    object DisablingSosSignalEvent : AppEvent()
}
