package com.girrafeecstud.signals.rescuers_impl.di

import com.girrafeecstud.location_tracker_api.data.LocationTrackerDataSource
import com.girrafeecstud.route_builder_api.data.RoutesDataSource
import retrofit2.Retrofit

interface RescuersFeatureDependencies {

    fun getRetrofit(): Retrofit

    fun getLocationTrackerDataSource(): LocationTrackerDataSource

    fun getRoutesDataSource(): RoutesDataSource

}