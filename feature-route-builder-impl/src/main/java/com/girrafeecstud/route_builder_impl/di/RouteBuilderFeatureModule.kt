package com.girrafeecstud.route_builder_impl.di

import android.content.Context
import com.girrafeecstud.route_builder_api.data.RouteBuilderClient
import com.girrafeecstud.route_builder_api.data.RoutesDataSource
import com.girrafeecstud.route_builder_impl.data.RouteBuilderClientImpl
import com.girrafeecstud.route_builder_impl.data.RoutesDataSourceImpl
import com.girrafeecstud.route_builder_impl.di.annotation.RouteBuilderFeatureScope
import com.girrafeecstud.route_builder_impl.extensions.setMeanAndGetInstance
import com.girrafeecstud.route_builder_impl.utils.RouteBuilderUtils
import dagger.Binds
import dagger.Module
import dagger.Provides
import org.osmdroid.bonuspack.routing.OSRMRoadManager

@Module(includes = [RouteBuilderFeatureModule.RouteBuilderFeatureBindModule::class])
class RouteBuilderFeatureModule {

    @RouteBuilderFeatureScope
    @Provides
    fun provideOSRMRoadManager(context: Context): OSRMRoadManager =
        OSRMRoadManager(context, RouteBuilderUtils.MAIN_ROAD_MANAGER_USER_AGENT)
            .setMeanAndGetInstance(mean = RouteBuilderUtils.ROAD_MANAGER_MEAN_BY_FOOT)

    @Module
    interface RouteBuilderFeatureBindModule {
        @RouteBuilderFeatureScope
        @Binds
        fun bindRouteBuilderClientImpl(impl: RouteBuilderClientImpl): RouteBuilderClient

        @RouteBuilderFeatureScope
        @Binds
        fun bindRouteBuilderDataSourceImpl(impl: RoutesDataSourceImpl): RoutesDataSource
    }
}