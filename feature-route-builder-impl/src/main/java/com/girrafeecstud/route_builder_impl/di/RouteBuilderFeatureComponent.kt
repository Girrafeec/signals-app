package com.girrafeecstud.route_builder_impl.di

import com.girrafeecstud.route_builder_api.di.RouteBuilderFeatureApi
import com.girrafeecstud.route_builder_impl.di.annotation.RouteBuilderFeatureScope
import dagger.Component

@RouteBuilderFeatureScope
@Component(
    modules =[RouteBuilderFeatureModule::class],
    dependencies = [RouteBuilderFeatureDependencies::class]
)
interface RouteBuilderFeatureComponent : RouteBuilderFeatureApi {

    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: RouteBuilderFeatureDependencies): Builder

        fun build(): RouteBuilderFeatureComponent

    }

    companion object {

        private var _routeBuilderFeatureComponent: RouteBuilderFeatureComponent? = null

        val routeBuilderFeatureComponent get() = _routeBuilderFeatureComponent

        fun init(dependencies: RouteBuilderFeatureDependencies) {
            if (_routeBuilderFeatureComponent == null)
                _routeBuilderFeatureComponent = DaggerRouteBuilderFeatureComponent
                    .builder()
                    .dependencies(dependencies = dependencies)
                    .build()
        }

        fun reset() {
            _routeBuilderFeatureComponent = null
        }

    }

}