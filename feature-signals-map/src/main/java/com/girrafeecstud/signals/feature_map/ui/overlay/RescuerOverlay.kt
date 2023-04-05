package com.girrafeecstud.signals.feature_map.ui.overlay

import android.content.Context
import android.graphics.*
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import com.girrafeecstud.core_ui.extension.loadAndSetImage
import com.girrafeecstud.signals.core_base.base.isInsidePolygon
import com.girrafeecstud.signals.core_base.base.roundTo
import com.girrafeecstud.signals.feature_map.databinding.RescuerMarkerBinding
import com.girrafeecstud.signals.feature_map.databinding.SosSignalMarkerBinding
import com.girrafeecstud.signals.feature_map.ui.RescuersClickEvent
import com.girrafeecstud.signals.feature_map.ui.SignalsClickEvent
import com.girrafeecstud.signals.rescuers_api.domain.Rescuer
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.Projection
import org.osmdroid.views.overlay.Overlay

class RescuerOverlay(
    private val context: Context,
    private val listener: RescuersClickEvent
) : BindingOverlay() {

    private var _binding: RescuerMarkerBinding? = null

    private val binding get() = _binding!!

    private var _rescuer: Rescuer? = null

    private var _geoPoint: GeoPoint? = null
    val geoPoint get() = _geoPoint

    override fun onAttach(mapView: MapView?) {
        _binding =
            RescuerMarkerBinding.inflate(LayoutInflater.from(context))
    }

    override fun onDetach(mapView: MapView?) {
        super.onDetach(mapView)
        _binding = null
    }

    fun setRescuer(rescuer: Rescuer) {
        _rescuer = rescuer
        _geoPoint = rescuer?.let { GeoPoint(it.rescuerLocationLatitude, it.rescuerLocationLongitude) }
    }

    override fun onSingleTapConfirmed(e: MotionEvent?, mapView: MapView?): Boolean {
        val projection = mapView?.projection ?: return false
        val touchedGeoPoint = projection.fromPixels(e?.x?.toInt() ?: 0, e?.y?.toInt() ?: 0)

        // Round the touchedGeoPoint to match the precision of the _geoPoint
        (touchedGeoPoint as GeoPoint).roundTo(numDecimalPlaces = 6)

        Log.i("tag", "rounded = $touchedGeoPoint")
        Log.i("tag", " _geoPoint = $_geoPoint")

        // Get bounds of image container view
        val hitRect = Rect()
        binding.rescuerImageContainer.getHitRect(hitRect)
        Log.i("tag", hitRect.toString())
        Log.i("tag", "hit rect: (${hitRect.left};${hitRect.top}); (${hitRect.right};${hitRect.top}); (${hitRect.left};${hitRect.bottom}); (${hitRect.right};${hitRect.bottom}) ")
        val point = projection.toPixels(_geoPoint, null)
        hitRect.offset(point.x - binding.rescuerImageContainer.width / 2, point.y - binding.rescuerImageContainer.height)
        Log.i("tag", hitRect.toString())

        Log.i("tag", "hit rect: (${hitRect.left};${hitRect.top}); (${hitRect.right};${hitRect.top}); (${hitRect.left};${hitRect.bottom}); (${hitRect.right};${hitRect.bottom}) ")
        Log.i("tag", " motion event ${e?.x?.toInt()}, ${e?.y?.toInt()}")

        if (touchedGeoPoint.isInsidePolygon(getRectGeoPoints(rect = hitRect, projection = projection))) {
            listener.onRescuerClick(rescuer = _rescuer)
            return true
        }

        // Check if the distance is less than circle radius
//        _geoPoint?.let {
////            Log.i("tag", "distance ${it.distanceToAsDouble(touchedGeoPoint).toFloat()}")
////            Log.i("tag", "circle radius $circleRadius")
//            if (it.distanceToAsDouble(touchedGeoPoint).toFloat() <= circleRadius) {
//                Log.i("tag", "in radius")
//                listener.onSignalClick(signal = _signal)
//                return true
//            }
//        }
        return false
    }

    override fun draw(pCanvas: Canvas?, pProjection: Projection?) {
        Log.i("tag", "is drawing")
        drawRescuerLocation(canvas = pCanvas, projection = pProjection)
    }

    private fun drawRescuerLocation(canvas: Canvas?, projection: Projection?) {
        _binding =
            RescuerMarkerBinding.inflate(LayoutInflater.from(context))
        binding.rescuerImage.loadAndSetImage(url = _rescuer?.rescuerProfileImageUrl)

        // Measure the view to make sure all the layout has been calculated
        binding.root.measure(
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        )
        binding.root.layout(0, 0, binding.root.measuredWidth, binding.root.measuredHeight)


        val bitmap = Bitmap.createBitmap(binding.root.width, binding.root.height, Bitmap.Config.ARGB_8888)
        val bitmapCanvas = Canvas(bitmap)
        binding.root.draw(bitmapCanvas)

        // Draw the resulting bitmap on the map's canvas
        val point = projection?.toPixels(_geoPoint, null)
        point?.let {
            canvas?.drawBitmap(
                bitmap,
                point.x - binding.root.width / 2f,
                point.y - binding.root.height.toFloat(),
                null
            )
        }
    }

    private fun getRectGeoPoints(rect: Rect, projection: Projection?): List<GeoPoint> {
        val leftTop = projection?.fromPixels(rect.left, rect.top)
        val rightTop = projection?.fromPixels(rect.right, rect.top)
        val leftBottom = projection?.fromPixels(rect.left, rect.bottom)
        val rightBottom = projection?.fromPixels(rect.right, rect.bottom)
        (leftTop as GeoPoint).roundTo(6)
        (rightTop as GeoPoint).roundTo(6)
        (leftBottom as GeoPoint).roundTo(6)
        (rightBottom as GeoPoint).roundTo(6)
        Log.i("tag", "rect coods: $leftTop; $rightTop; $leftBottom; $rightBottom")
        return listOf(leftTop, rightTop, leftBottom, rightBottom)
    }

}