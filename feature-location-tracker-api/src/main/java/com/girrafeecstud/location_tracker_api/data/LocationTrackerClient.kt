package com.girrafeecstud.location_tracker_api.data

import android.location.Location
import kotlinx.coroutines.flow.Flow

interface LocationTrackerClient {

    fun getLocationUpdates(): Flow<Location>

}