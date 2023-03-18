package com.girrafeecstud.signals.location_tracker_impl

import android.location.Location
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal fun Flow<Location>.uniqueLocation(): Flow<Location> =
    flow {
        var lastLocation: Location? = null
        collect { location ->
            if (
                lastLocation?.latitude != location.latitude ||
                        lastLocation?.longitude != location.longitude
            ) {
                lastLocation = location
                emit(location)
            }
        }
    }