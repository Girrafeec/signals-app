package com.girrafeecstud.signals.signals_impl.di

import android.content.Context
import com.girrafeecstud.location_tracker_api.data.BaseLocationTrackerDataSource
import com.girrafeecstud.signals.auth_api.data.IAuthDataSource
import retrofit2.Retrofit
import javax.inject.Named

interface SignalsFeatureDependencies {

    fun getRetrofit(): Retrofit

    fun getAuthDataSource(): IAuthDataSource

    fun getContext(): Context

    @Named("LOCATION_TRACKER_DATASOURCE")
    fun getLocationTrackerDataSource(): BaseLocationTrackerDataSource

}