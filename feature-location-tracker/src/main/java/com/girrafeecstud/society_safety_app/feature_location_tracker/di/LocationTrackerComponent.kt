package com.girrafeecstud.society_safety_app.feature_location_tracker.di

import com.girrafeecstud.society_safety_app.core_network.data.di.CoreNetworkApi
import com.girrafeecstud.society_safety_app.core_preferences.di.CorePreferencesApi
import com.girrafeecstud.society_safety_app.feature_location_tracker.data.di.LocationTrackerDataSourceModule
import com.girrafeecstud.society_safety_app.feature_location_tracker.data.di.LocationTrackerRepositoryModule
import com.girrafeecstud.society_safety_app.feature_location_tracker.data.di.UserLocationTrackerApiModule
import com.girrafeecstud.society_safety_app.feature_location_tracker.di.annotation.LocationTrackerScope
import com.girrafeecstud.society_safety_app.feature_location_tracker.di.dependencies.LocationTrackerDependencies
import dagger.Component

@LocationTrackerScope
@Component(
    modules = [
        UserLocationTrackerApiModule::class,
        LocationTrackerModule::class,
        LocationTrackerDataSourceModule::class,
        LocationTrackerRepositoryModule::class
              ],
    dependencies = [
        LocationTrackerDependencies::class
    ]
)
interface LocationTrackerComponent : LocationTrackerApi {

    @Component.Builder
    interface Builder {

        fun locationTrackerDependencies(dependencies: LocationTrackerDependencies): Builder

        fun build(): LocationTrackerComponent

    }

    companion object {

        private var _locationTrackerComponent: LocationTrackerComponent? = null

        val locationTrackerComponent get() = _locationTrackerComponent!!

        fun init(dependencies: LocationTrackerDependencies) {
            if (_locationTrackerComponent == null)
                _locationTrackerComponent = DaggerLocationTrackerComponent
                    .builder()
                    .locationTrackerDependencies(dependencies = dependencies)
                    .build()
        }

        fun reset() {
            _locationTrackerComponent = null
        }

    }

    @LocationTrackerScope
    @Component(
        dependencies = [
            CoreNetworkApi::class,
            CorePreferencesApi::class
        ]
    )
    interface LocationTrackerDependenciesComponent : LocationTrackerDependencies

}