package com.girrafeecstud.signals.location_tracker_impl.engine

import com.girrafeecstud.location_tracker_api.domain.entity.UserLocation

data class LocationTrackerState(
    val location: UserLocation? = null
)
