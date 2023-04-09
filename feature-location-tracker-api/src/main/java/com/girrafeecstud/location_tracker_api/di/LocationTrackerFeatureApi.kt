package com.girrafeecstud.location_tracker_api.di

import com.girrafeecstud.location_tracker_api.data.BaseLocationTrackerDataSource
import com.girrafeecstud.location_tracker_api.domain.GetLastKnownLocationUseCase
import com.girrafeecstud.location_tracker_api.engine.LocationTrackerEngine
import javax.inject.Named

interface LocationTrackerFeatureApi {

    @Named("LOCATION_TRACKER_DATASOURCE")
    fun getLocationTrackerDataSource(): BaseLocationTrackerDataSource

    fun getLastKnownLocationUseCase(): GetLastKnownLocationUseCase

    fun getLocationTrackerEngine(): LocationTrackerEngine

}