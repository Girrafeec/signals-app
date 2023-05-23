/* Created by Girrafeec */

package com.girrafeecstud.signals.location_tracker_impl.data.datasource

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.girrafeecstud.location_tracker_api.data.BaseLocationTrackerDataSource
import com.girrafeecstud.location_tracker_api.domain.entity.UserLocation
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalLocationTrackerDataSource @Inject constructor(
    private val applicationContext: Context
) : BaseLocationTrackerDataSource() {

    private var _sharedPreferences: SharedPreferences? = null

    private val sharedPreferences get () = _sharedPreferences!!

    companion object {
        private const val LOCATION_SHARED_PREFERENCES = "LOCATION_SHARED_PREFERENCES"
        private const val LAST_LOCATION_LATITUDE = "LAST_LOCATION_LATITUDE"
        private const val LAST_LOCATION_LONGITUDE = "LAST_LOCATION_LONGITUDE"
        private const val LAST_SENT_LOCATION_LATITUDE = "LAST_SENT_LOCATION_LATITUDE"
        private const val LAST_SENT_LOCATION_LONGITUDE = "LAST_SENT_LOCATION_LONGITUDE"
    }

    init {
        _sharedPreferences = applicationContext.getSharedPreferences(
            LOCATION_SHARED_PREFERENCES,
            AppCompatActivity.MODE_PRIVATE
        )
    }

    override fun getLastKnownLocation(): Flow<BusinessResult<UserLocation>> =
        flow {
            val latitude = sharedPreferences.getFloat(LAST_LOCATION_LATITUDE, 0.0f)
            val longitude = sharedPreferences.getFloat(LAST_LOCATION_LONGITUDE, 0.0f)
//            if (latitude != 0.0f && longitude != 0.0f)
//                emit()
            emit(BusinessResult.Success(_data = UserLocation(latitude = latitude.toDouble(), longitude = longitude.toDouble())))
        }

    override fun saveLastKnownLocation(location: UserLocation) {
        Log.i("tag locations", "save loc")
        sharedPreferences
            .edit()
            .putFloat(LAST_LOCATION_LATITUDE, location.latitude.toFloat())
            .putFloat(LAST_LOCATION_LONGITUDE, location.longitude.toFloat())
            .apply()
    }

    override fun saveLastSentLocation(location: UserLocation) {
        Log.i("tag locations", "save sent loc")
        sharedPreferences
            .edit()
            .putFloat(LAST_SENT_LOCATION_LATITUDE, location.latitude.toFloat())
            .putFloat(LAST_SENT_LOCATION_LONGITUDE, location.longitude.toFloat())
            .apply()
    }

    override fun getLastSentLocation(): Flow<BusinessResult<UserLocation>> =
        flow {
            val latitude = sharedPreferences.getFloat(LAST_SENT_LOCATION_LATITUDE, 0.0f)
            val longitude = sharedPreferences.getFloat(LAST_SENT_LOCATION_LONGITUDE, 0.0f)
//            if (latitude != 0.0f && longitude != 0.0f)
//                emit()
            emit(BusinessResult.Success(_data = UserLocation(latitude = latitude.toDouble(), longitude = longitude.toDouble())))
        }
}