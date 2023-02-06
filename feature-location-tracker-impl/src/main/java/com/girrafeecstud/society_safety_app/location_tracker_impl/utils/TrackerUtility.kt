package com.girrafeecstud.society_safety_app.location_tracker_impl.utils

import com.google.android.gms.location.Priority

internal object TrackerUtility {
    const val DEFAULT_LOCATION_TRACKER_INTERVAL = 4000L
    const val MIN_LOCATION_TRACKER_INTERVAL = DEFAULT_LOCATION_TRACKER_INTERVAL / 2
    const val DEFAULT_LOCATION_TRACKER_PRIORITY = Priority.PRIORITY_HIGH_ACCURACY
}