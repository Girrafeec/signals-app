package com.girrafeecstud.signals.event_bus.di

import com.girrafeecstud.signals.event_bus.EventBus

interface EventBusApi {

    fun getEventBus(): EventBus

}