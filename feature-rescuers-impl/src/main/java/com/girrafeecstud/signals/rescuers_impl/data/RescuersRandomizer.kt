/* Created by Girrafeec */

package com.girrafeecstud.signals.rescuers_impl.data

import android.util.Log
import com.girrafeecstud.location_tracker_api.data.BaseLocationTrackerDataSource
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.rescuers_api.domain.Rescuer
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Named

class RescuersRandomizer @Inject constructor(
    @Named("LOCATION_TRACKER_DATASOURCE")
    private val locationDataSource: BaseLocationTrackerDataSource
) {

    val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val rescuersTempList = listOf(
        Rescuer(
            rescuerId = "ce0c4cf7-9968-4155-bea2-e310b1248b08",
            rescuerFirstName = "Alex",
            rescuerLastName = "Born",
            rescuerPhoneNumber = "+79234556985",
            rescuerProfileImageUrl = "https://i0.wp.com/xn--tomo-o83cuf7jj61w54ryvgb31m.com/wp-content/uploads/2022/04/pexels-photo-7562313.jpeg?w=867&ssl=1",
            rescuerLocationLatitude = 59.9003,
            rescuerLocationLongitude = 30.4891
        ),
        Rescuer(
            rescuerId = "16866392-53b1-4398-8a11-5cadfb559e7d",
            rescuerFirstName = "Randall",
            rescuerLastName = "Boggs",
            rescuerPhoneNumber = "+79999999999",
            rescuerProfileImageUrl = "https://static.wikia.nocookie.net/pixar/images/8/86/Randall.png/revision/latest/scale-to-width-down/1000?cb=20160404023111",
            rescuerLocationLatitude = 60.029972,
            rescuerLocationLongitude = 30.631602
        )
    )

    private fun getRandomNumber(): Int {
        return (0..10).random()
    }

    private fun getSignalsRandomNumber(): Int {
        val number = getRandomNumber()
        if (number in 1..8)
            return 1
        else
            return 2
    }

    private fun getLatitude(latitude: Double, longitude: Double, distance: Double): Double {
        val R = 6371000.0 // Earth's radius in meters
        val lat1Rad = Math.toRadians(latitude)
        val lon1Rad = Math.toRadians(longitude)
        val lat2Rad = Math.asin(Math.sin(lat1Rad) * Math.cos(distance / R) + Math.cos(lat1Rad) * Math.sin(distance / R) * Math.cos(0.0))
        return Math.toDegrees(lat2Rad)
    }

    private fun getLongitude(latitude: Double, longitude: Double, distance: Double): Double {
        val R = 6371000.0 // Earth's radius in meters
        val lat1Rad = Math.toRadians(latitude)
        val lon1Rad = Math.toRadians(longitude)
        val dLonRad = Math.asin(Math.sin(distance / R) / Math.cos(lat1Rad))
        val lon2Rad = lon1Rad + dLonRad
        return Math.toDegrees(lon2Rad)
    }

    private fun getRescuersWithLocation(
        signalsNumber: Int
    ): Flow<List<Rescuer>> =
        flow {
            val result = locationDataSource.getLastKnownLocation().take(1).last()
            if (result is BusinessResult.Success) {
                val location = result._data!!

                Log.i("tag resc list", "generate location")

                if (signalsNumber == 1) {
                    rescuersTempList.get(0).rescuerLocationLatitude = getLatitude(latitude = location.latitude, longitude = location.longitude, distance = 100.0)
                    rescuersTempList.get(0).rescuerLocationLongitude = location.longitude
                    emit(listOf(rescuersTempList.get(0)))
                }
                else if (signalsNumber == 2) {
                    rescuersTempList.get(0).rescuerLocationLatitude = getLatitude(latitude = location.latitude, longitude = location.longitude, distance = 100.0)
                    rescuersTempList.get(0).rescuerLocationLongitude = location.longitude

                    rescuersTempList.get(1).rescuerLocationLongitude = getLongitude(latitude = location.latitude, longitude = location.longitude, distance = 150.0)
                    rescuersTempList.get(1).rescuerLocationLatitude = location.latitude
                    emit(rescuersTempList)
                }
            }

        }

    fun getRescuers(): Flow<List<Rescuer>> =
        flow {
            val signalsNumber = getSignalsRandomNumber()

            Log.i("tag resc list number", signalsNumber.toString())

            if (signalsNumber == 1)
                emit(getRescuersWithLocation(1).last())
            if (signalsNumber == 2)
                emit(getRescuersWithLocation(2).last())

//            emit(emptyList<EmergencySignal>())
        }.flowOn(Dispatchers.IO)

    fun getRescuerDetails(
        rescuerId: String
    ): Flow<BusinessResult<Rescuer>> =
        flow {
            delay(2000)
            for (rescuer in rescuersTempList)
                if (rescuer.rescuerId == rescuerId)
                    emit(BusinessResult.Success(_data = rescuer))
        }.flowOn(Dispatchers.IO)

}