package com.girrafeecstud.signals.signal_details_impl.di

import com.girrafeecstud.signals.rescuer_mode_api.di.RescuerModeFeatureApi
import com.girrafeecstud.signals.signal_details_impl.di.annotation.SignalDetailsFeatureScope
import com.girrafeecstud.signals.signal_details_api.di.SignalDetailsFeatureApi
import com.girrafeecstud.signals.signal_details_impl.ui.SignalDetailsFragment
import com.girrafeecstud.signals.signal_details_impl.ui.SignalDetailsParentFragment
import com.girrafeecstud.signals.signals_api.di.SignalsFeatureApi
import dagger.Component

@SignalDetailsFeatureScope
@Component(
    modules = [SignalDetailsFeatureModule::class],
    dependencies = [SignalDetailsFeatureDependencies::class]
)
interface SignalDetailsFeatureComponent : SignalDetailsFeatureApi {


    //TODO разобраться с проблемой жизненного цикла ViewModel

    fun inject(fragment: SignalDetailsParentFragment)

//    fun injectSignalsDetailsFragment(fragment: SignalDetailsFragment)

    fun signalDetailsComponent(): SignalDetailsComponent.Builder

    fun receiverComponent(): SignalDetailsFeatureReceiverComponent.Builder

    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: SignalDetailsFeatureDependencies): Builder

        fun build(): SignalDetailsFeatureComponent

    }

    companion object {

        private var _signalDetailsFeatureComponent: SignalDetailsFeatureComponent? = null

        val signalDetailsFeatureComponent get() = _signalDetailsFeatureComponent!!

        fun init(dependencies: SignalDetailsFeatureDependencies) {
            if (_signalDetailsFeatureComponent == null) {
                _signalDetailsFeatureComponent = DaggerSignalDetailsFeatureComponent
                    .builder()
                    .dependencies(dependencies = dependencies)
                    .build()
            }
        }

        fun reset() {
            _signalDetailsFeatureComponent = null
        }

    }

    @SignalDetailsFeatureScope
    @Component(
        dependencies = [
            SignalsFeatureApi::class,
            RescuerModeFeatureApi::class
        ]
    )
    interface SignalDetailsFeatureDependenciesComponent : SignalDetailsFeatureDependencies

}