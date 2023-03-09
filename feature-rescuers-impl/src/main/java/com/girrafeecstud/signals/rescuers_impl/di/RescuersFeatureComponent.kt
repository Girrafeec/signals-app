package com.girrafeecstud.signals.rescuers_impl.di

import com.girrafeecstud.signals.rescuers_api.di.RescuersFeatureApi
import com.girrafeecstud.signals.rescuers_impl.di.annotation.RescuersFeatureScope
import com.girrafeecstud.society_safety_app.core_network.data.di.CoreNetworkApi
import dagger.Component
import dagger.Component.Builder

@RescuersFeatureScope
@Component(
    modules = [RescuersFeatureModule::class],
    dependencies = [RescuersFeatureDependencies::class]
)
interface RescuersFeatureComponent : RescuersFeatureApi {

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
            CoreNetworkApi::class
        ]
    )
    interface RescuersFeatureDependenciesComponent : RescuersFeatureDependencies

}