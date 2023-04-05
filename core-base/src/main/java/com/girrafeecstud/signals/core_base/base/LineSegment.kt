/* Created by Girrafeec */

package com.girrafeecstud.signals.core_base.base

import org.osmdroid.util.GeoPoint

class LineSegment(
    private val start: GeoPoint,
    private val end: GeoPoint
    ) {
    fun intersects(other: LineSegment): Boolean {
        val dx1 = end.longitude - start.longitude
        val dy1 = end.latitude - start.latitude
        val dx2 = other.end.longitude - other.start.longitude
        val dy2 = other.end.latitude - other.start.latitude
        val delta = dx1 * dy2 - dy1 * dx2
        // If delta is 0 it means that lines are parallel and can not intersect
        if (delta == 0.0) {
            return false
        }
        // Line 1: x = x1 + t1 * (x2 - x1), y = y1 + t1 * (y2 - y1)
        // Line 2: x = x3 + t2 * (x4 - x3), y = y3 + t2 * (y4 - y3)
        // Find out if t1 and t2 are between0  and 1.
        // If s is greater than 1, it means that the intersection
        // point is located beyond the end of the line segment defined by start and end.
        // Similarly, if s is less than 0, it means that the intersection
        // point is located before the start of the line segment.
        val t1 = (dx1 * (other.start.latitude - start.latitude) - dy1 * (other.start.longitude - start.longitude)) / delta
        val t2 = (dx2 * (start.latitude - other.start.latitude) - dy2 * (start.longitude - other.start.longitude)) / (-delta)
        return t1 in 0.0..1.0 && t2 in 0.0..1.0
    }
}