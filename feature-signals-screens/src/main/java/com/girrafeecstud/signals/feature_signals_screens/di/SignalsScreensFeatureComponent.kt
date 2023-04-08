package com.girrafeecstud.signals.feature_signals_screens.di

import com.girrafeecstud.countdown_timer_api.di.CountDownTimerFeatureApi
import com.girrafeecstud.signals.event_bus.di.EventBusApi
import com.girrafeecstud.signals.feature_signals_screens.di.annotation.SignalsFeatureScope
import com.girrafeecstud.signals.feature_signals_screens.di.dependencies.SignalsScreensFeatureDependencies
import com.girrafeecstud.signals.feature_signals_screens.ui.SignalsFlowFragment
import com.girrafeecstud.sos_signal_api.di.SosSignalFeatureApi
import dagger.Component

@SignalsFeatureScope
@Component(
    modules = [SignalsScreensFeatureModule::class],
    dependencies = [SignalsScreensFeatureDependencies::class]
)
interface SignalsScreensFeatureComponent {

    fun inject(fragment: SignalsFlowFragment)

    fun sosSignalComponent(): SosSignalComponent.Builder

    fun sosCountDownComponent(): SosCountDownComponent.Builder

    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: SignalsScreensFeatureDependencies): Builder

        fun build(): SignalsScreensFeatureComponent

    }

    companion object {

        private var _signalsScreensFeatureComponent: SignalsScreensFeatureComponent? = null

        val signalsFeatureComponent get() = _signalsScreensFeatureComponent!!

        fun init(dependencies: SignalsScreensFeatureDependencies) {
            if (_signalsScreensFeatureComponent == null)
                _signalsScreensFeatureComponent = DaggerSignalsScreensFeatureComponent.builder()
                    .dependencies(dependencies = dependencies)
                    .build()
        }

        fun reset() {
            _signalsScreensFeatureComponent = null
        }

    }

    @SignalsFeatureScope
    @Component(
        dependencies = [
            SosSignalFeatureApi::class,
            EventBusApi::class,
            CountDownTimerFeatureApi::class
        ]
    )
    interface SignalsScreensFeatureDependenciesComponent : SignalsScreensFeatureDependencies
}