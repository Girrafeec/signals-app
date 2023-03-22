package com.girrafeecstud.signals.feature_map.ui

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.girrafeecstud.signals.rescuers_api.domain.Rescuer
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.Projection
import org.osmdroid.views.overlay.Overlay

class RescuerOverlay(
    private val context: Context
) : Overlay() {

    private var _rescuer: Rescuer? = null

    private var _geoPoint: GeoPoint? = null
    val geoPoint get() = _geoPoint

    fun setRescuer(rescuer: Rescuer) {
        _rescuer = rescuer
        _geoPoint = rescuer?.let { GeoPoint(it.rescuerLocationLatitude, it.rescuerLocationLongitude) }
    }

    override fun draw(pCanvas: Canvas?, pProjection: Projection?) {
        Log.i("tag", "is drawing")
        drawRescuerLocation(canvas = pCanvas, projection = pProjection)
    }

    private fun drawRescuerLocation(canvas: Canvas?, projection: Projection?) {
        val point = Point()
        projection?.toPixels(_geoPoint, point)
        val paint = Paint()
        paint.style = Paint.Style.FILL
        paint.color = Color.RED
//        projection.save(canvas,true, false)
        canvas?.drawCircle(point.x.toFloat(), point.y.toFloat(), 25.0.toFloat(), paint)
    }


}