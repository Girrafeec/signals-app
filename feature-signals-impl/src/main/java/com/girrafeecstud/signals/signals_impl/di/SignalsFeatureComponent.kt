package com.girrafeecstud.signals.signals_impl.di

import com.girrafeecstud.core_components.di.CoreComponentsApi
import com.girrafeecstud.location_tracker_api.di.LocationTrackerFeatureApi
import com.girrafeecstud.signals.auth_api.di.AuthFeatureApi
import com.girrafeecstud.signals.core_network.data.di.CoreNetworkApi
import com.girrafeecstud.signals.rescuers_impl.di.annotation.SignalsFeatureScope
import com.girrafeecstud.signals.signals_api.di.SignalsFeatureApi
import com.girrafeecstud.signals.signals_impl.service.SignalsService
import dagger.Component

@SignalsFeatureScope
@Component(
    modules = [SignalsFeatureModule::class],
    dependencies = [SignalsFeatureDependencies::class]
)
interface SignalsFeatureComponent : SignalsFeatureApi {

    fun inject(service: SignalsService)

    fun receiverComponent(): SignalsFeatureReceiverComponent.Builder

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
                _signalsFeatureComponent = DaggerSignalsFeatureComponent
                    .builder()
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
            CoreNetworkApi::class,
            LocationTrackerFeatureApi::class,
            AuthFeatureApi::class,
            CoreComponentsApi::class
        ]
    )
    interface SignalsFeatureDependenciesComponent : SignalsFeatureDependencies

}