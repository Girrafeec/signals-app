package com.girrafeecstud.society_safety_app.app

import android.app.Application
import android.content.Context
import com.girrafeecstud.society_safety_app.core_base.di.CoreBaseComponent
import com.girrafeecstud.society_safety_app.core_network.data.di.CoreNetworkComponent
import com.girrafeecstud.society_safety_app.core_network.data.di.NetworkDependencies
import com.girrafeecstud.society_safety_app.core_preferences.di.CorePreferencesComponent
import com.girrafeecstud.society_safety_app.core_preferences.di.dependencies.CorePreferencesDependencies
import com.girrafeecstud.society_safety_app.di.AppComponent
import com.girrafeecstud.society_safety_app.di.AppDependencies
import com.girrafeecstud.society_safety_app.di.DaggerAppComponent
import com.girrafeecstud.society_safety_app.feature_map.di.DaggerMainComponent_MainDependenciesComponent
import com.girrafeecstud.society_safety_app.feature_map.di.MainComponent
import com.girrafeecstud.society_safety_app.feature_signals.di.DaggerSignalsFeatureComponent_SignalsFeatureDependenciesComponent
import com.girrafeecstud.society_safety_app.feature_signals.di.SignalsFeatureComponent
import com.girrafeecstud.society_safety_app.location_tracker_impl.di.LocationTrackerFeatureComponent
import com.girrafeecstud.society_safety_app.location_tracker_impl.di.dependencies.LocationTrackerDependencies
import com.girrafeecstud.sos_signal_api.di.SosSignalFeatureApi
import com.girrafeecstud.sos_signal_impl.di.SosSignalFeatureComponent
import com.girrafeecstud.sos_signal_impl.di.dependencies.SosSignalDependencies

class SocietySafetyApp : Application() {

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
        LocationTrackerFeatureComponent.init(LocationTrackerDependenciesImpl())
        // TODO how to reset class?
        SosSignalFeatureComponent.init(dependencies = SosSignalPedendenciesImpl())
        MainComponent.init(dependencies = DaggerMainComponent_MainDependenciesComponent
            .builder()
            .corePreferencesApi(CorePreferencesComponent.corePreferencesComponent)
            .locationTrackerFeatureApi(LocationTrackerFeatureComponent.locationTrackerFeatureComponent)
            .sosSignalFeatureApi(SosSignalFeatureComponent.sosSignalFeatureComponent)
            .build()
        )
        // TODO how to reset class?
        SignalsFeatureComponent.init(dependencies = DaggerSignalsFeatureComponent_SignalsFeatureDependenciesComponent
            .builder()
            .sosSignalFeatureApi(SosSignalFeatureComponent.sosSignalFeatureComponent)
            .build()
        )
    }

    private inner class AppDependenciesImpl: AppDependencies {
        override val applicationContext: Context = this@SocietySafetyApp
    }

    // TODO реализовать отправку контекста из appComponent
    private inner class NetworkDependenciesImpl: NetworkDependencies {
        override val applicationContext: Context = this@SocietySafetyApp
    }

    private inner class CorePreferencesDependenciesImpl: CorePreferencesDependencies {
        override val applicationContext: Context = this@SocietySafetyApp
    }

    private inner class LocationTrackerDependenciesImpl: LocationTrackerDependencies {
        override val applicationContext: Context = this@SocietySafetyApp
    }

    private inner class SosSignalPedendenciesImpl: SosSignalDependencies {
        override val applicationContext: Context = this@SocietySafetyApp
    }

    // TODO what to do with whese components when application is destroyed?
}