/* Created by Girrafeec */

package com.girrafeecstud.location_tracker_api.utils

import com.girrafeecstud.location_tracker_api.domain.entity.UserLocation

import kotlin.math.*

fun UserLocation.distanceTo(location: UserLocation): Double {
    val earthRadius = 6371.0 // Radius of the Earth in kilometers

    val lat1Rad = Math.toRadians(this.latitude)
    val lat2Rad = Math.toRadians(location.latitude)
    val latDiffRad = Math.toRadians(location.latitude - this.latitude)
    val lonDiffRad = Math.toRadians(location.longitude - this.longitude)

    val a = sin(latDiffRad / 2).pow(2) +
            cos(lat1Rad) * cos(lat2Rad) * sin(lonDiffRad / 2).pow(2)
    val c = 2 * atan2(sqrt(a), sqrt(1 - a))

    return earthRadius * c * 1000 // Convert distance to meters
}