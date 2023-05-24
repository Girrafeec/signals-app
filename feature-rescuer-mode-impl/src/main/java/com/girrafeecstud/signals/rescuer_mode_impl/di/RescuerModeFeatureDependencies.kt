package com.girrafeecstud.signals.rescuer_mode_impl.di

import android.content.Context
import com.girrafeecstud.location_tracker_api.data.BaseLocationTrackerDataSource
import com.girrafeecstud.route_builder_api.data.RoutesDataSource
import com.girrafeecstud.signals.auth_api.data.IAuthDataSource
import retrofit2.Retrofit
import javax.inject.Named

interface RescuerModeFeatureDependencies {

    fun getContext(): Context

    fun getRetrofit(): Retrofit

    fun getAuthDataSource(): IAuthDataSource

    fun getRoutesDataSource(): RoutesDataSource

    @Named("LOCATION_TRACKER_DATASOURCE")
    fun getLocationTrackerDataSource(): BaseLocationTrackerDataSource

}
