package com.girrafeecstud.signals.rescuers_impl.di

import com.girrafeecstud.location_tracker_api.data.BaseLocationTrackerDataSource
import com.girrafeecstud.route_builder_api.data.RoutesDataSource
import com.girrafeecstud.signals.auth_api.data.IAuthDataSource
import retrofit2.Retrofit
import javax.inject.Named

interface RescuersFeatureDependencies {

    fun getRetrofit(): Retrofit

    @Named("LOCATION_TRACKER_DATASOURCE")
    fun getLocationTrackerDataSource(): BaseLocationTrackerDataSource

    fun getRoutesDataSource(): RoutesDataSource

    fun getAuthDataSource(): IAuthDataSource

}