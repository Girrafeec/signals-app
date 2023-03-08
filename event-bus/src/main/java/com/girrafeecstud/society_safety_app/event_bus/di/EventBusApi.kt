package com.girrafeecstud.society_safety_app.event_bus.di

import com.girrafeecstud.society_safety_app.event_bus.EventBus

interface EventBusApi {

    fun getEventBus(): EventBus

}