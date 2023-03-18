package com.girrafeecstud.signals.app

import android.app.Application
import android.content.Context
import com.girrafeecstud.route_builder_impl.di.RouteBuilderFeatureComponent
import com.girrafeecstud.route_builder_impl.di.RouteBuilderFeatureDependencies
import com.girrafeecstud.signals.rescuer_details_impl.di.RescuerDetailsFeatureComponent
import com.girrafeecstud.signals.rescuers_impl.di.DaggerRescuersFeatureComponent_RescuersFeatureDependenciesComponent
import com.girrafeecstud.signals.rescuers_impl.di.RescuersFeatureComponent
import com.girrafeecstud.signals.rescuers_list_impl.di.DaggerRescuersListFeatureComponent_RescuersListFeatureDependenciesComponent
import com.girrafeecstud.signals.rescuers_list_impl.di.RescuersListFeatureComponent
import com.girrafeecstud.signals.core_base.di.CoreBaseComponent
import com.girrafeecstud.signals.core_network.data.di.CoreNetworkComponent
import com.girrafeecstud.signals.core_network.data.di.NetworkDependencies
import com.girrafeecstud.signals.core_preferences.di.CorePreferencesComponent
import com.girrafeecstud.signals.core_preferences.di.dependencies.CorePreferencesDependencies
import com.girrafeecstud.signals.di.AppComponent
import com.girrafeecstud.signals.di.AppDependencies
import com.girrafeecstud.signals.di.DaggerAppComponent
import com.girrafeecstud.signals.event_bus.di.EventBusComponent
import com.girrafeecstud.signals.feature_map.di.DaggerMainComponent_MainDependenciesComponent
import com.girrafeecstud.signals.feature_map.di.MainComponent
import com.girrafeecstud.signals.feature_signals.di.DaggerSignalsFeatureComponent_SignalsFeatureDependenciesComponent
import com.girrafeecstud.signals.feature_signals.di.SignalsFeatureComponent
import com.girrafeecstud.signals.location_tracker_impl.di.LocationTrackerFeatureComponent
import com.girrafeecstud.signals.location_tracker_impl.di.dependencies.LocationTrackerDependencies
import com.girrafeecstud.sos_signal_impl.di.DaggerSosSignalFeatureComponent_SosSignalFeatureDependenciesComponent
import com.girrafeecstud.sos_signal_impl.di.SosSignalFeatureComponent

class SignalsApp : Application() {

    lateinit var appComponent: AppComponent

    lateinit var networkComponent: CoreNetworkComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .appDependencies(AppDependenciesImpl())
            .build()

        // TODO подумать над тем, как это делать иначе
        CoreNetworkComponent.init(networkDependencies = NetworkDependenciesImpl())
        CorePreferencesComponent.init(preferencesDependencies = CorePreferencesDependenciesImpl())
        CoreBaseComponent.init()
        EventBusComponent.init()
        LocationTrackerFeatureComponent.init(LocationTrackerDependenciesImpl())
        RouteBuilderFeatureComponent.init(RouteBuilderDependenciesImpl())
        // TODO how to reset class?
        SosSignalFeatureComponent.init(
            dependencies = DaggerSosSignalFeatureComponent_SosSignalFeatureDependenciesComponent
                .builder()
                .eventBusApi(EventBusComponent.eventBusComponent)
                .build()
        )
        RescuersFeatureComponent.init(
            dependencies = DaggerRescuersFeatureComponent_RescuersFeatureDependenciesComponent
                .builder()
                .coreNetworkApi(CoreNetworkComponent.coreNetworkComponent)
                .locationTrackerFeatureApi(LocationTrackerFeatureComponent.locationTrackerFeatureComponent)
                .routeBuilderFeatureApi(RouteBuilderFeatureComponent.routeBuilderFeatureComponent)
                .build()
        )
        RescuersListFeatureComponent.init(
            dependencies = DaggerRescuersListFeatureComponent_RescuersListFeatureDependenciesComponent
                .builder()
                .rescuersFeatureApi(RescuersFeatureComponent.rescuersFeatureComponent)
                .build()
        )
        RescuerDetailsFeatureComponent.init()
        MainComponent.init(dependencies = DaggerMainComponent_MainDependenciesComponent
            .builder()
            .corePreferencesApi(CorePreferencesComponent.corePreferencesComponent)
            .locationTrackerFeatureApi(LocationTrackerFeatureComponent.locationTrackerFeatureComponent)
            .sosSignalFeatureApi(SosSignalFeatureComponent.sosSignalFeatureComponent)
            .rescuersFeatureApi(RescuersFeatureComponent.rescuersFeatureComponent)
            .rescuersListFeatureApi(RescuersListFeatureComponent.rescuersListFeatureComponent)
            .rescuerDetailsFeatureApi(RescuerDetailsFeatureComponent.rescuerDetailsFeatureComponent)
            .eventBusApi(EventBusComponent.eventBusComponent)
            .build()
        )
        // TODO how to reset class?
        SignalsFeatureComponent.init(dependencies = DaggerSignalsFeatureComponent_SignalsFeatureDependenciesComponent
            .builder()
            .sosSignalFeatureApi(SosSignalFeatureComponent.sosSignalFeatureComponent)
            .eventBusApi(EventBusComponent.eventBusComponent)
            .build()
        )
    }

    private inner class AppDependenciesImpl: AppDependencies {
        override val applicationContext: Context = this@SignalsApp
    }

    // TODO реализовать отправку контекста из appComponent
    private inner class NetworkDependenciesImpl: NetworkDependencies {
        override val applicationContext: Context = this@SignalsApp
    }

    private inner class CorePreferencesDependenciesImpl: CorePreferencesDependencies {
        override val applicationContext: Context = this@SignalsApp
    }

    private inner class LocationTrackerDependenciesImpl: LocationTrackerDependencies {
        override val applicationContext: Context = this@SignalsApp
    }

    private inner class RouteBuilderDependenciesImpl: RouteBuilderFeatureDependencies {
        override fun getContext(): Context = this@SignalsApp
    }

//    private inner class SosSignalPedendenciesImpl: SosSignalDependencies {
//        override val applicationContext: Context = this@SocietySafetyApp
//    }

    // TODO what to do with whese components when application is destroyed?
}