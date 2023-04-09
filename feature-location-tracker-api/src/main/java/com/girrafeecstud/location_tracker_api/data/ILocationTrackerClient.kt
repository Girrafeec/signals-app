package com.girrafeecstud.location_tracker_api.data

import android.location.Location
import kotlinx.coroutines.flow.Flow

interface ILocationTrackerClient {

    fun getLocationUpdates(): Flow<Location>

}