package com.girrafeecstud.signals.event_bus.di

import com.girrafeecstud.signals.event_bus.EventBus
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class EventBusModule {

    @Provides
    @Singleton
    fun provideEventBus() = EventBus()

}