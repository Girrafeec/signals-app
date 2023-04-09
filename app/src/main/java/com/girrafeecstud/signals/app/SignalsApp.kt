package com.girrafeecstud.signals.app

import android.app.Application
import android.content.Context
import android.util.Log
import com.girrafeecstud.core_components.di.CoreComponentsComponent
import com.girrafeecstud.core_components.di.ICoreComponentsDependencies
import com.girrafeecstud.countdown_timer_impl.di.CountDownTimerFeatureComponent
import com.girrafeecstud.route_builder_impl.di.RouteBuilderFeatureComponent
import com.girrafeecstud.route_builder_impl.di.RouteBuilderFeatureDependencies
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
import com.girrafeecstud.signals.feature_signals_screens.di.SignalsScreensFeatureComponent
import com.girrafeecstud.signals.feature_signals_screens.di.DaggerSignalsScreensFeatureComponent_SignalsScreensFeatureDependenciesComponent
import com.girrafeecstud.signals.location_tracker_impl.di.LocationTrackerFeatureComponent
import com.girrafeecstud.signals.location_tracker_impl.di.dependencies.LocationTrackerDependencies
import com.girrafeecstud.signals.rescuer_details_impl.di.component.DaggerRescuerDetailsFeatureComponent_RescuerDetailsFeatureDependenciesComponent
import com.girrafeecstud.signals.rescuer_details_impl.di.component.RescuerDetailsFeatureComponent
import com.girrafeecstud.signals.signal_details_impl.di.DaggerSignalDetailsFeatureComponent_SignalDetailsFeatureDependenciesComponent
import com.girrafeecstud.signals.signal_details_impl.di.SignalDetailsFeatureComponent
import com.girrafeecstud.signals.signals_impl.di.DaggerSignalsFeatureComponent_SignalsFeatureDependenciesComponent
import com.girrafeecstud.signals.signals_impl.di.SignalsFeatureComponent
import com.girrafeecstud.sos_signal_impl.di.DaggerSosSignalFeatureComponent_SosSignalFeatureDependenciesComponent
import com.girrafeecstud.sos_signal_impl.di.SosSignalFeatureComponent
import com.google.firebase.messaging.FirebaseMessaging

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
        CoreComponentsComponent.init(dependencies = CoreComponentsDependencies())
        EventBusComponent.init()
        CountDownTimerFeatureComponent.init()
        LocationTrackerFeatureComponent.init(LocationTrackerDependenciesImpl())
        RouteBuilderFeatureComponent.init(RouteBuilderDependenciesImpl())
        // TODO how to reset class?
        SosSignalFeatureComponent.init(
            dependencies = DaggerSosSignalFeatureComponent_SosSignalFeatureDependenciesComponent
                .builder()
                .eventBusApi(EventBusComponent.eventBusComponent)
                .countDownTimerFeatureApi(CountDownTimerFeatureComponent.countDownTimerFeatureComponent)
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
        RescuerDetailsFeatureComponent.init(
            dependencies = DaggerRescuerDetailsFeatureComponent_RescuerDetailsFeatureDependenciesComponent
                .builder()
                .rescuersFeatureApi(RescuersFeatureComponent.rescuersFeatureComponent)
                .build()
        )
        SignalsFeatureComponent.init(
            dependencies = DaggerSignalsFeatureComponent_SignalsFeatureDependenciesComponent
                .builder()
                .coreNetworkApi(CoreNetworkComponent.coreNetworkComponent)
                .locationTrackerFeatureApi(LocationTrackerFeatureComponent.locationTrackerFeatureComponent)
                .build()
        )
        SignalDetailsFeatureComponent.init(dependencies = DaggerSignalDetailsFeatureComponent_SignalDetailsFeatureDependenciesComponent
            .builder()
            .signalsFeatureApi(SignalsFeatureComponent.signalsFeatureComponent)
            .build()
        )
        MainComponent.init(dependencies = DaggerMainComponent_MainDependenciesComponent
            .builder()
            .corePreferencesApi(CorePreferencesComponent.corePreferencesComponent)
            .coreComponentsApi(CoreComponentsComponent.coreComponentsComponent)
            .locationTrackerFeatureApi(LocationTrackerFeatureComponent.locationTrackerFeatureComponent)
            .sosSignalFeatureApi(SosSignalFeatureComponent.sosSignalFeatureComponent)
            .rescuersFeatureApi(RescuersFeatureComponent.rescuersFeatureComponent)
            .rescuersListFeatureApi(RescuersListFeatureComponent.rescuersListFeatureComponent)
            .rescuerDetailsFeatureApi(RescuerDetailsFeatureComponent.rescuerDetailsFeatureComponent)
            .signalsFeatureApi(SignalsFeatureComponent.signalsFeatureComponent)
            .signalDetailsFeatureApi(SignalDetailsFeatureComponent.signalDetailsFeatureComponent)
            .eventBusApi(EventBusComponent.eventBusComponent)
            .build()
        )
        // TODO how to reset class?
        SignalsScreensFeatureComponent.init(dependencies = DaggerSignalsScreensFeatureComponent_SignalsScreensFeatureDependenciesComponent
            .builder()
            .sosSignalFeatureApi(SosSignalFeatureComponent.sosSignalFeatureComponent)
            .eventBusApi(EventBusComponent.eventBusComponent)
            .countDownTimerFeatureApi(CountDownTimerFeatureComponent.countDownTimerFeatureComponent)
            .build()
        )

        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            Log.i("tag", "task ${task.isSuccessful}")
            if (!task.isSuccessful)
                return@addOnCompleteListener
            val token = task.result
            Log.i("tag", "token $token")
        }
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

    private inner class CoreComponentsDependencies : ICoreComponentsDependencies {
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