/* Created by Girrafeec */

package com.girrafeecstud.signals.signals_impl.data

import android.util.Log
import com.girrafeecstud.location_tracker_api.data.BaseLocationTrackerDataSource
import com.girrafeecstud.location_tracker_api.domain.entity.UserLocation
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.signals_api.domain.entity.EmergencySignal
import com.girrafeecstud.signals.signals_api.domain.entity.EmergencySignalType
import com.girrafeecstud.signals.signals_api.domain.entity.SignalSender
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Named

class SignalsRandomizer @Inject constructor(
    @Named("LOCATION_TRACKER_DATASOURCE")
    private val locationDataSource: BaseLocationTrackerDataSource
) {

    val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val signalsTempList = listOf(
        EmergencySignal(
            signalId = "b495e37c-e138-49d9-a981-676ef67abbef",
            signalStartTimestamp = "2023-03-24T09:42:12+03:00",
            signalLatitude = 59.905851,
            signalLongitude = 30.483845,
            emergencySignalType = EmergencySignalType.DEFAULT_SOS_SIGNAL,
            emergencySignalTitle = "Нужна помощь!",
            emergencySignalDescription = "Описание",
            signalSender = SignalSender(
                signalSenderId = "ca0ab149-010f-42a4-9ea8-e0964046aa8f",
                signalSenderFirstName = "Mike",
                signalSenderLastName = "Brown",
                signalSenderProfileImageUrl = "https://images.unsplash.com/photo-1544723795-3fb6469f5b39?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=689&q=80"
            )
        ),
        EmergencySignal(
            signalId = "5206db30-36a0-4d3f-9b38-fcde8cf31174",
            signalStartTimestamp = "2023-03-24T10:42:12+03:00",
            signalLatitude = 59.901484,
            signalLongitude = 30.482426,
            emergencySignalType = EmergencySignalType.HEART_ATTACK_SIGNAL,
            emergencySignalTitle = "Нужна помощь!",
            emergencySignalDescription = "Описание",
            signalSender = SignalSender(
                signalSenderId = "1f56dc27-e35b-4f0d-909c-ecabd9acaef1",
                signalSenderFirstName = "Max",
                signalSenderLastName = "Larsen",
                signalSenderProfileImageUrl = "https://images.unsplash.com/photo-1639747280929-e84ef392c69a?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80"
            )
        )
    )

    private fun getRandomNumber(): Int {
        return (0..10).random()
    }

    private fun getSignalsRandomNumber(): Int {
        val number = getRandomNumber()
        if (number < 2)
            return 0
        if (number in 3..6)
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

    private fun getSignalsWithLocation(
        signalsNumber: Int
    ): Flow<List<EmergencySignal>> =
        flow {
            val result = locationDataSource.getLastKnownLocation().take(1).last()
            if (result is BusinessResult.Success) {
                val location = result._data!!

                Log.i("tag signals list", "generate location")

                if (signalsNumber == 1) {
                    signalsTempList.get(0).signalLatitude = getLatitude(latitude = location.latitude, longitude = location.longitude, distance = 100.0)
                    signalsTempList.get(0).signalLongitude = location.longitude
                    emit(listOf(signalsTempList.get(0)))
                }
                else if (signalsNumber == 2) {
                    signalsTempList.get(0).signalLatitude = getLatitude(latitude = location.latitude, longitude = location.longitude, distance = 100.0)
                    signalsTempList.get(0).signalLongitude = location.longitude

                    signalsTempList.get(1).signalLongitude = getLongitude(latitude = location.latitude, longitude = location.longitude, distance = 150.0)
                    signalsTempList.get(1).signalLatitude = location.latitude
                    emit(signalsTempList)
                }
            }

        }

    fun getSignals(): Flow<List<EmergencySignal>> =
        flow {
            val signalsNumber = getSignalsRandomNumber()

            Log.i("tag signals list number", signalsNumber.toString())

            if (signalsNumber == 1)
                emit(getSignalsWithLocation(1).last())
            if (signalsNumber == 2)
                emit(getSignalsWithLocation(2).last())

//            emit(emptyList<EmergencySignal>())
        }.flowOn(Dispatchers.IO)

    fun getSignalDetails(
        signalId: String
    ): Flow<BusinessResult<EmergencySignal>> =
        flow {
            delay(2000)
            for (signal in signalsTempList)
                if (signal.signalId == signalId)
                    emit(BusinessResult.Success(_data = signal))
        }.flowOn(Dispatchers.IO)

}