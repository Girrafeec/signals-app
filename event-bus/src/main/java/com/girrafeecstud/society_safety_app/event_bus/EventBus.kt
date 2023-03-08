package com.girrafeecstud.society_safety_app.event_bus

import android.util.Log
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

class EventBus @Inject constructor() {

    private var _events = MutableSharedFlow<AppEvent>(replay=10)

    val events = _events.asSharedFlow()

    suspend fun invokeEvent(event: AppEvent) {
        Log.i("tag", "event bus: ${_events.replayCache}")
        _events.emit(event)
    }

}