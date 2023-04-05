/* Created by Girrafeec */

package com.girrafeecstud.signals.core_base.base

import android.util.Log
import org.osmdroid.util.GeoPoint

fun GeoPoint.roundTo(numDecimalPlaces: Int) {
    latitude = latitude.roundTo(numDecimalPlaces = numDecimalPlaces)
    longitude = longitude.roundTo(numDecimalPlaces = numDecimalPlaces)
}

// Method to find out if GeoPoint is inside Polyon
fun GeoPoint.isInsidePolygon(polygonPoints: List<GeoPoint>): Boolean {

    var intersections = 0

    // Create a horizontal line that passes through the point
    val horizontalLine = LineSegment(
        GeoPoint(latitude, -180.0),
        GeoPoint(latitude, 180.0)
    )

    for (i in polygonPoints.indices) {
        val j = (i + 1) % polygonPoints.size
        val side = LineSegment(polygonPoints[i], polygonPoints[j])
        if (horizontalLine.intersects(side)) {
            intersections++
        }
    }

    // If the number of intersections is odd, the point is inside the polygon
    return intersections % 2 != 0
}