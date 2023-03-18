package com.girrafeecstud.location_tracker_api.di

import com.girrafeecstud.location_tracker_api.data.LocationTrackerDataSource
import com.girrafeecstud.location_tracker_api.domain.GetLastKnownLocationUseCase
import com.girrafeecstud.location_tracker_api.engine.LocationTrackerEngine

interface LocationTrackerFeatureApi {

    fun getLocationTrackerDataSource(): LocationTrackerDataSource

    fun getLastKnownLocationUseCase(): GetLastKnownLocationUseCase

    fun getLocationTrackerEngine(): LocationTrackerEngine

}