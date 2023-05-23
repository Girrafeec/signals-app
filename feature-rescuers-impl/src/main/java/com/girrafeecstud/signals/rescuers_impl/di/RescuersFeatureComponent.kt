package com.girrafeecstud.signals.rescuers_impl.di

import com.girrafeecstud.location_tracker_api.di.LocationTrackerFeatureApi
import com.girrafeecstud.route_builder_api.di.RouteBuilderFeatureApi
import com.girrafeecstud.signals.auth_api.di.AuthFeatureApi
import com.girrafeecstud.signals.rescuers_api.di.RescuersFeatureApi
import com.girrafeecstud.signals.rescuers_impl.di.annotation.RescuersFeatureScope
import com.girrafeecstud.signals.core_network.data.di.CoreNetworkApi
import com.girrafeecstud.signals.rescuers_impl.service.RescuersService
import dagger.Component

@RescuersFeatureScope
@Component(
    modules = [RescuersFeatureModule::class],
    dependencies = [RescuersFeatureDependencies::class]
)
interface RescuersFeatureComponent : RescuersFeatureApi {

    fun inject(service: RescuersService)

    fun receiverComponent(): RescuersFeatureReceiverComponent.Builder

    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: RescuersFeatureDependencies): Builder

        fun build(): RescuersFeatureComponent

    }

    companion object {

        private var _rescuersFeatureComponent: RescuersFeatureComponent? = null

        val rescuersFeatureComponent get() = _rescuersFeatureComponent!!

        fun init(dependencies: RescuersFeatureDependencies) {
            if (_rescuersFeatureComponent == null)
                _rescuersFeatureComponent = DaggerRescuersFeatureComponent
                    .builder()
                    .dependencies(dependencies = dependencies)
                    .build()
        }

        fun reset() {
            _rescuersFeatureComponent = null
        }

    }

    @RescuersFeatureScope
    @Component(
        dependencies = [
            CoreNetworkApi::class,
            LocationTrackerFeatureApi::class,
            RouteBuilderFeatureApi::class,
            AuthFeatureApi::class
        ]
    )
    interface RescuersFeatureDependenciesComponent : RescuersFeatureDependencies

}