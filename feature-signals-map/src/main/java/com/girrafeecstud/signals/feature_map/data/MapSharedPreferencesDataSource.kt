/* Created by Girrafeec */

package com.girrafeecstud.signals.feature_map.data

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.girrafeecstud.signals.feature_map.utils.MapUtils
import org.osmdroid.api.IGeoPoint
import org.osmdroid.util.GeoPoint
import javax.inject.Inject

class MapSharedPreferencesDataSource @Inject constructor(
    private val applicationContext: Context
) {

    private var _sharedPreferences: SharedPreferences? = null

    private val sharedPreferences get () = _sharedPreferences!!

    companion object {
        private const val MAP_SHARED_PREFERENCES = "MAP_SHARED_PREFERENCES"
        private const val MAP_LAST_CENTER_POINT_LATITUDE = "MAP_LAST_CENTER_POINT_LATITUDE"
        private const val MAP_LAST_CENTER_POINT_LONGITUDE = "MAP_LAST_CENTER_POINT_LONGITUDE"
        private const val MAP_LAST_ZOOM = "MAP_LAST_ZOOM"
    }

    init {
        _sharedPreferences = applicationContext.getSharedPreferences(
            MAP_SHARED_PREFERENCES,
            AppCompatActivity.MODE_PRIVATE
        )
    }

    suspend fun getMapLastCenterPoint(): GeoPoint {
        val latitude = sharedPreferences.getFloat(MAP_LAST_CENTER_POINT_LATITUDE, 0.0f)
        val longitude = sharedPreferences.getFloat(MAP_LAST_CENTER_POINT_LONGITUDE, 0.0f)
        return GeoPoint(latitude.toDouble(), longitude.toDouble())
    }

    suspend fun setMapLastCenterPoint(geoPoint: IGeoPoint) {
        sharedPreferences
            .edit()
            .putFloat(MAP_LAST_CENTER_POINT_LATITUDE, geoPoint.latitude.toFloat())
            .putFloat(MAP_LAST_CENTER_POINT_LONGITUDE, geoPoint.longitude.toFloat())
            .apply()
    }

    suspend fun getMapLastZoom(): Double =
        sharedPreferences.getFloat(MAP_LAST_ZOOM, MapUtils.DEFAULT_MIN_ZOOM_LEVEL.toFloat()).toDouble()

    suspend fun setMapLastZoom(zoom: Double) {
        sharedPreferences
            .edit()
            .putFloat(MAP_LAST_ZOOM, zoom.toFloat())
            .apply()
    }

}