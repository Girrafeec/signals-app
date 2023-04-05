package com.girrafeecstud.signals.feature_map.ui.overlay

import android.content.Context
import android.graphics.*
import android.util.Log
import androidx.core.content.ContextCompat
import com.girrafeecstud.location_tracker_api.domain.entity.UserLocation
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.Projection
import org.osmdroid.views.overlay.Overlay

class CurrentLocationOverlay(
    private val context: Context
) : Overlay() {

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
        val drawable = ContextCompat.getDrawable(context, com.girrafeecstud.core_ui.R.drawable.orange_white_stroked_circle)
        val size = dpToPx(30f).toInt() // convert dp to pixels
        drawable?.setBounds(
            point.x - size / 2,
            point.y - size / 2,
            point.x + size / 2,
            point.y + size / 2
        )
        drawable?.draw(canvas!!)
    }

    // function to convert dp to pixels
    private fun dpToPx(dp: Float): Float {
        return dp * context.resources.displayMetrics.density
    }

}