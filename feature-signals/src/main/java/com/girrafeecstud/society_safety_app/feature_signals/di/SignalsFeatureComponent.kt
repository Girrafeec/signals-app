package com.girrafeecstud.society_safety_app.feature_signals.di

import com.girrafeecstud.society_safety_app.feature_signals.di.annotation.SignalsFeatureScope
import com.girrafeecstud.society_safety_app.feature_signals.di.dependencies.SignalsFeatureDependencies
import com.girrafeecstud.society_safety_app.feature_signals.ui.SignalsFlowFragment
import com.girrafeecstud.society_safety_app.feature_signals.ui.SosSignalFragment
import com.girrafeecstud.sos_signal_api.di.SosSignalFeatureApi
import dagger.Component

@SignalsFeatureScope
@Component(
    modules = [],
    dependencies = [SignalsFeatureDependencies::class]
)
interface SignalsFeatureComponent {

    fun inject(fragment: SignalsFlowFragment)

    fun injectSos(fragment: SosSignalFragment)

    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: SignalsFeatureDependencies): Builder

        fun build(): SignalsFeatureComponent

    }

    companion object {

        private var _signalsFeatureComponent: SignalsFeatureComponent? = null

        val signalsFeatureComponent get() = _signalsFeatureComponent!!

        fun init(dependencies: SignalsFeatureDependencies) {
            if (_signalsFeatureComponent == null)
                _signalsFeatureComponent = DaggerSignalsFeatureComponent.builder()
                    .dependencies(dependencies = dependencies)
                    .build()
        }

        fun reset() {
            _signalsFeatureComponent = null
        }

    }

    @SignalsFeatureScope
    @Component(
        dependencies = [
            SosSignalFeatureApi::class
        ]
    )
    interface SignalsFeatureDependenciesComponent : SignalsFeatureDependencies
}