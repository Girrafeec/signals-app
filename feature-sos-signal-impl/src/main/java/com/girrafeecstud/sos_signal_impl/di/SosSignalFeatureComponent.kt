package com.girrafeecstud.sos_signal_impl.di

import com.girrafeecstud.countdown_timer_api.di.CountDownTimerFeatureApi
import com.girrafeecstud.signals.event_bus.di.EventBusApi
import com.girrafeecstud.sos_signal_api.di.SosSignalFeatureApi
import com.girrafeecstud.sos_signal_impl.di.annotation.SosSignalScope
import com.girrafeecstud.sos_signal_impl.di.dependencies.SosSignalDependencies
import com.girrafeecstud.sos_signal_impl.service.SosSignalService
import dagger.Component

@SosSignalScope
@Component(
    modules = [SosSignalModule::class],
    dependencies = [SosSignalDependencies::class]
)
interface SosSignalFeatureComponent : SosSignalFeatureApi {

    fun inject(service: SosSignalService)

    @Component.Builder
    interface Builder {

        fun sosSignalDependencies(dependencies: SosSignalDependencies): Builder

        fun build(): SosSignalFeatureComponent

    }

    companion object {

        private var _sosSignalFeatureComponent: SosSignalFeatureComponent? = null

        val sosSignalFeatureComponent get() = _sosSignalFeatureComponent!!

        fun init(dependencies: SosSignalDependencies) {
            if (_sosSignalFeatureComponent == null)
                _sosSignalFeatureComponent = DaggerSosSignalFeatureComponent
                    .builder()
                    .sosSignalDependencies(dependencies = dependencies)
                    .build()
        }

        fun reset() {
            _sosSignalFeatureComponent = null
        }
    }

    @SosSignalScope
    @Component(
        dependencies = [
            EventBusApi::class,
            CountDownTimerFeatureApi::class
        ]
    )
    interface SosSignalFeatureDependenciesComponent : SosSignalDependencies
}