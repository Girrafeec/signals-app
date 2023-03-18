package com.girrafeecstud.signals.location_tracker_impl.di

import com.girrafeecstud.location_tracker_api.di.LocationTrackerFeatureApi
import com.girrafeecstud.signals.location_tracker_impl.di.annotation.LocationTrackerScope
import com.girrafeecstud.signals.location_tracker_impl.di.dependencies.LocationTrackerDependencies
import com.girrafeecstud.signals.location_tracker_impl.service.LocationTrackerService
import dagger.Component

@LocationTrackerScope
@Component(
    modules = [
        LocationTrackerModule::class
              ],
    dependencies = [
        LocationTrackerDependencies::class
    ]
)
interface LocationTrackerFeatureComponent : LocationTrackerFeatureApi {

    fun inject(service: LocationTrackerService)

    fun receiverComponent(): LocationTrackerReceiverComponent.Builder

    @Component.Builder
    interface Builder {

        fun locationTrackerDependencies(dependencies: LocationTrackerDependencies): Builder

        fun build(): LocationTrackerFeatureComponent

    }

    companion object {

        private var _locationTrackerFeatureComponent: LocationTrackerFeatureComponent? = null

        val locationTrackerFeatureComponent get() = _locationTrackerFeatureComponent!!

        fun init(dependencies: LocationTrackerDependencies) {
            if (_locationTrackerFeatureComponent == null)
                _locationTrackerFeatureComponent = DaggerLocationTrackerFeatureComponent
                    .builder()
                    .locationTrackerDependencies(dependencies = dependencies)
                    .build()
        }

        fun reset() {
            _locationTrackerFeatureComponent = null
        }

    }

//    @LocationTrackerScope
//    @Component(
//        dependencies = [
////            CoreNetworkApi::class,
////            CorePreferencesApi::class
//        ]
//    )
//    interface LocationTrackerDependenciesComponent : LocationTrackerDependencies

}