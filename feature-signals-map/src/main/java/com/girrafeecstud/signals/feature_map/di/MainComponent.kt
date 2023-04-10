package com.girrafeecstud.signals.feature_map.di

import com.girrafeecstud.core_components.di.CoreComponentsApi
import com.girrafeecstud.location_tracker_api.di.LocationTrackerFeatureApi
import com.girrafeecstud.on_board.di.OnBoardFeatureApi
import com.girrafeecstud.on_board.di.OnBoardFeatureComponent
import com.girrafeecstud.signals.rescuer_details_api.di.RescuerDetailsFeatureApi
import com.girrafeecstud.signals.rescuers_api.di.RescuersFeatureApi
import com.girrafeecstud.signals.rescuers_list_api.di.RescuersListFeatureApi
import com.girrafeecstud.signals.core_base.presentation.base.di.BaseViewModelFactoryModule
import com.girrafeecstud.signals.core_preferences.di.CorePreferencesApi
import com.girrafeecstud.signals.event_bus.di.EventBusApi
import com.girrafeecstud.signals.feature_map.di.annotation.MapsFeatureScope
import com.girrafeecstud.signals.feature_map.di.dependencies.MainDependencies
import com.girrafeecstud.signals.feature_map.ui.MapsFlowFragment
import com.girrafeecstud.signals.signal_details_api.di.SignalDetailsFeatureApi
import com.girrafeecstud.signals.signals_api.di.SignalsFeatureApi
import com.girrafeecstud.sos_signal_api.di.SosSignalFeatureApi
import dagger.Component


// TODO переименовать потом
@MapsFeatureScope
@Component(
    modules = [
        MainModule::class,
        BaseViewModelFactoryModule::class
    ],
    dependencies = [
        MainDependencies::class
    ]
)
interface MainComponent {

    fun inject(fragment: MapsFlowFragment)

    fun mapComponent(): MapComponent.Builder

    fun signalsMapComponent(): SignalsMapComponent.Builder

    fun sosMapComponent(): SosMapComponent.Builder

    @Component.Builder
    interface Builder {

        fun build(): MainComponent

        fun mainComponentDependencies(dependencies: MainDependencies): Builder

    }

    companion object {

        private var _mainComponent: MainComponent? = null

        val mainComponent get() = _mainComponent!!

        fun init(dependencies: MainDependencies) {
            if (_mainComponent == null)
                _mainComponent = DaggerMainComponent
                    .builder()
                    .mainComponentDependencies(dependencies = dependencies)
                    .build()
        }

        fun reset() {
            _mainComponent = null
        }

    }

    @Component(
        dependencies = [
            CorePreferencesApi::class,
            CoreComponentsApi::class,
            LocationTrackerFeatureApi::class,
            SosSignalFeatureApi::class,
            RescuersFeatureApi::class,
            RescuersListFeatureApi::class,
            RescuerDetailsFeatureApi::class,
            SignalsFeatureApi::class,
            SignalDetailsFeatureApi::class,
            EventBusApi::class,
            OnBoardFeatureApi::class
        ]
    )
    interface MainDependenciesComponent: MainDependencies

}