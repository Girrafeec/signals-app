package com.girrafeecstud.route_builder_impl.extensions

import android.content.Context
import org.osmdroid.bonuspack.routing.OSRMRoadManager

fun OSRMRoadManager.setMeanAndGetInstance(
    mean: String
): OSRMRoadManager {
    this.setMean(mean)
    return this
}

