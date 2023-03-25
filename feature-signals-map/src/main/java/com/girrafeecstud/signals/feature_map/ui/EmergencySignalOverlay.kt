package com.girrafeecstud.signals.feature_map.ui

import android.content.Context
import android.graphics.*
import android.util.Log
import android.view.MotionEvent
import com.girrafeecstud.signals.core_base.base.roundTo
import com.girrafeecstud.signals.signals_api.domain.entity.EmergencySignal
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.Projection
import org.osmdroid.views.overlay.Overlay

class EmergencySignalOverlay(
    private val context: Context,
    private val listener: SignalsClickEvent
) : Overlay() {

    companion object {
        private const val circleRadius = 25.0f
    }

    private var _signal: EmergencySignal? = null

    private var _geoPoint: GeoPoint? = null
    val geoPoint get() = _geoPoint

    fun setSignal(signal: EmergencySignal) {
        _signal = signal
        _geoPoint = signal.let { GeoPoint(it.signalLatitude, it.signalLongitude) }
    }

    override fun draw(pCanvas: Canvas?, pProjection: Projection?) {
        Log.i("tag", "is drawing")
        drawSignalLocation(canvas = pCanvas, projection = pProjection)
    }

    override fun onSingleTapConfirmed(e: MotionEvent?, mapView: MapView?): Boolean {
        val projection = mapView?.projection ?: return false
        val touchedGeoPoint = projection.fromPixels(e?.x?.toInt() ?: 0, e?.y?.toInt() ?: 0)

        // Round the touchedGeoPoint to match the precision of the _geoPoint
        val roundedTouchedGeoPoint = GeoPoint(
            touchedGeoPoint.latitude.roundTo(6),
            touchedGeoPoint.longitude.roundTo(6)
        )

        // Calculate the distance between the two points
//        val distance = roundedTouchedGeoPoint.distanceTo(_geoPoint)

        Log.i("tag", "rounded = $roundedTouchedGeoPoint")
        Log.i("tag", " _geoPoint = $_geoPoint")

        // Check if the distance is less than some tolerance value (in meters)
        _geoPoint?.let {
//            Log.i("tag", "distance ${it.distanceToAsDouble(roundedTouchedGeoPoint).toFloat()}")
//            Log.i("tag", "circle radius $circleRadius")
            if (it.distanceToAsDouble(roundedTouchedGeoPoint).toFloat() <= circleRadius) {
                Log.i("tag", "in radius")
                listener.onSignalClick(signal = _signal)
                return true
            }
        }
        return false
    }

    private fun drawSignalLocation(canvas: Canvas?, projection: Projection?) {
        val point = Point()
        projection?.toPixels(_geoPoint, point)
        val paint = Paint()
        paint.style = Paint.Style.FILL
        paint.color = Color.RED
//        projection.save(canvas,true, false)
        canvas?.drawCircle(point.x.toFloat(), point.y.toFloat(), circleRadius, paint)
    }

}
