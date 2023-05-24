/* Created by Girrafeec */

package com.girrafeecstud.signals.rescuer_mode_impl.di

import com.girrafeecstud.core_components.di.CoreComponentsApi
import com.girrafeecstud.location_tracker_api.di.LocationTrackerFeatureApi
import com.girrafeecstud.route_builder_api.di.RouteBuilderFeatureApi
import com.girrafeecstud.signals.auth_api.di.AuthFeatureApi
import com.girrafeecstud.signals.core_network.data.di.CoreNetworkApi
import com.girrafeecstud.signals.core_network.data.di.CoreNetworkComponent
import com.girrafeecstud.signals.rescuer_mode_api.di.RescuerModeFeatureApi
import com.girrafeecstud.signals.rescuer_mode_impl.service.RescuerModeService
import dagger.Component

@RescuerModeFeatureScope
@Component(
    modules = [RescuerModeFeatureModule::class],
    dependencies = [RescuerModeFeatureDependencies::class]
)
interface RescuerModeFeatureComponent : RescuerModeFeatureApi {

    fun inject(service: RescuerModeService)

    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: RescuerModeFeatureDependencies): Builder

        fun build(): RescuerModeFeatureComponent

    }

    companion object {

        private var _rescuerModeFeatureComponent: RescuerModeFeatureComponent? = null

        val rescuerModeFeatureComponent get() = _rescuerModeFeatureComponent!!

        fun init(dependencies: RescuerModeFeatureDependencies) {
            if (_rescuerModeFeatureComponent == null)
                _rescuerModeFeatureComponent = DaggerRescuerModeFeatureComponent
                    .builder()
                    .dependencies(dependencies = dependencies)
                    .build()
        }

        fun reset() {
            _rescuerModeFeatureComponent = null
        }

    }

    @RescuerModeFeatureScope
    @Component(
        dependencies = [
            CoreComponentsApi::class,
            CoreNetworkApi::class,
            RouteBuilderFeatureApi::class,
            AuthFeatureApi::class,
            LocationTrackerFeatureApi::class
        ]
    )
    interface RescuerModeFeatureDependenciesComponent : RescuerModeFeatureDependencies

}