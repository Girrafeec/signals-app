package com.girrafeecstud.signals.feature_map.ui

import android.graphics.*
import android.util.Log
import com.girrafeecstud.location_tracker_api.domain.entity.UserLocation
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.Projection
import org.osmdroid.views.overlay.Overlay
import javax.inject.Inject

class CurrentLocationOverlay @Inject constructor() : Overlay() {

    private var _location: UserLocation? = null

    private var _geoPoint: GeoPoint? = null
    val geoPoint get() = _geoPoint

    fun setLocation(location: UserLocation?) {
        _location = location
        _geoPoint = location?.let { GeoPoint(it.latitude, it.longitude) }
    }

    override fun draw(pCanvas: Canvas?, pProjection: Projection?) {
        Log.i("tag", "is drawing")
        drawMyLocation(canvas = pCanvas, projection = pProjection)
    }

    private fun drawMyLocation(canvas: Canvas?, projection: Projection?) {
        val point = Point()
        projection?.toPixels(_geoPoint, point)
        val paint = Paint()
        paint.style = Paint.Style.FILL
        paint.color = Color.BLUE
//        projection.save(canvas,true, false)
        canvas?.drawCircle(point.x.toFloat(), point.y.toFloat(), 25.0.toFloat(), paint)
    }

}