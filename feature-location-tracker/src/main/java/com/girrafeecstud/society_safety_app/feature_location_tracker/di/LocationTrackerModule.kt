package com.girrafeecstud.society_safety_app.feature_location_tracker.di

import com.girrafeecstud.society_safety_app.feature_location_tracker.data.network.mapper.UserLocationEntityDtoMapper
import com.girrafeecstud.society_safety_app.feature_location_tracker.di.annotation.LocationTrackerScope
import dagger.Module
import dagger.Provides

@Module
class LocationTrackerModule {

    @Provides
    @LocationTrackerScope
    fun provideUserLocationEntityDtoMapper(): UserLocationEntityDtoMapper = UserLocationEntityDtoMapper()

}