package com.girrafeecstud.signals.location_tracker_impl.utils

import com.google.android.gms.location.Priority

internal object TrackerUtility {
    const val DEFAULT_LOCATION_TRACKER_INTERVAL = 4000L
    const val MIN_LOCATION_TRACKER_INTERVAL = DEFAULT_LOCATION_TRACKER_INTERVAL / 2
    const val DEFAULT_LOCATION_TRACKER_PRIORITY = Priority.PRIORITY_HIGH_ACCURACY
    const val LOCAL_LOCATION_TRACKER_DATASOURCE = "LOCAL_LOCATION_TRACKER_DATASOURCE"
    const val LOCATION_TRACKER_DATASOURCE = "LOCATION_TRACKER_DATASOURCE"
}