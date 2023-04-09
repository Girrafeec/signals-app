package com.girrafeecstud.signals.signals_impl.di

import com.girrafeecstud.location_tracker_api.data.BaseLocationTrackerDataSource
import retrofit2.Retrofit
import javax.inject.Named

interface SignalsFeatureDependencies {

    fun getRetrofit(): Retrofit

    @Named("LOCATION_TRACKER_DATASOURCE")
    fun getLocationTrackerDataSource(): BaseLocationTrackerDataSource

}