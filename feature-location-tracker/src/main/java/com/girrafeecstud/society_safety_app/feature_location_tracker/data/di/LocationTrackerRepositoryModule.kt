package com.girrafeecstud.society_safety_app.feature_location_tracker.data.di

import com.girrafeecstud.society_safety_app.feature_location_tracker.data.repository.LocationTrackerRepositoryImpl
import com.girrafeecstud.society_safety_app.feature_location_tracker.di.annotation.LocationTrackerScope
import com.girrafeecstud.society_safety_app.feature_location_tracker.domain.repository.LocationTrackerRepository
import dagger.Binds
import dagger.Module

@Module
abstract class LocationTrackerRepositoryModule {

    @Binds
    @LocationTrackerScope
    abstract fun bindLocationTrackerRepositoryimpl(impl: LocationTrackerRepositoryImpl): LocationTrackerRepository

}