package com.girrafeecstud.signals.event_bus.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [EventBusModule::class]
)
interface EventBusComponent : EventBusApi {

    @Component.Builder
    interface Builder {

        fun build(): EventBusComponent

    }

    companion object {

        private var _eventBusComponent: EventBusComponent? = null

        val eventBusComponent get() = _eventBusComponent!!

        fun init() {
            if (_eventBusComponent == null) {
                _eventBusComponent = DaggerEventBusComponent
                    .builder()
                    .build()
            }
        }

        fun reset() {
            _eventBusComponent = null
        }

    }

}