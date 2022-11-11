package com.girrafeecstud.society_safety_app.feature_location_tracker.data.di

import com.girrafeecstud.society_safety_app.feature_location_tracker.data.datasource.LocationTrackerDataSource
import com.girrafeecstud.society_safety_app.feature_location_tracker.data.datasource.LocationTrackerDataSourceImpl
import com.girrafeecstud.society_safety_app.feature_location_tracker.di.annotation.LocationTrackerScope
import dagger.Binds
import dagger.Module

@Module
abstract class LocationTrackerDataSourceModule {

    @Binds
    @LocationTrackerScope
    abstract fun bindLocationTrackerDataSourceImpl(impl: LocationTrackerDataSourceImpl): LocationTrackerDataSource

}