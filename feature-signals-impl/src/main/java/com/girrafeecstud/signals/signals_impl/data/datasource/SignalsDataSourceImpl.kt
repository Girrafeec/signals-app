package com.girrafeecstud.signals.signals_impl.data.datasource

import android.util.Log
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.signals_api.domain.entity.SignalSender
import com.girrafeecstud.signals.signals_api.domain.entity.EmergencySignal
import com.girrafeecstud.signals.signals_api.domain.entity.EmergencySignalType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.delay
import javax.inject.Inject

class SignalsDataSourceImpl @Inject constructor(

) : SignalsDataSource {

    private val signalTempDetails1 =
        EmergencySignal(
            signalId = "b495e37c-e138-49d9-a981-676ef67abbef",
            signalStartTimestamp = "2023-03-24T09:42:12+03:00",
            signalLatitude = 59.905851,
            signalLongitude = 30.483845,
            emergencySignalType = EmergencySignalType.DEFAULT_SOS_SIGNAL,
            emergencySignalTitle = "Нужна помощь!",
            emergencySignalDescription = "Описание",
            signalSender =
            SignalSender(
                signalSenderId = "ca0ab149-010f-42a4-9ea8-e0964046aa8f",
                signalSenderFirstName = "Mike",
                signalSenderLastName = "Brown",
                signalSenderProfileImageUrl = "https://images.unsplash.com/photo-1544723795-3fb6469f5b39?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=689&q=80"
            )
        )

    private val signalTempDetails2 =
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

    override fun getSignalsList(token: String): Flow<BusinessResult<List<EmergencySignal>>> =
        flow {
            Log.i("tag", "ask signals")
            delay(3000)
            emit(BusinessResult.Success(_data = signalsTempList))
        }.flowOn(Dispatchers.IO)

    override fun getSignalDetails(
        token: String,
        signalId: String
    ): Flow<BusinessResult<EmergencySignal>> =
        flow {
            Log.i("tag", "ask signal details")
            delay(2000)
            if (signalId == "b495e37c-e138-49d9-a981-676ef67abbef")
                emit(BusinessResult.Success(_data = signalTempDetails1))
            else
                emit(BusinessResult.Success(_data = signalTempDetails2))
        }.flowOn(Dispatchers.IO)
}