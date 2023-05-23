package com.girrafeecstud.signals.signals_impl.data.network.dto

import com.girrafeecstud.signals.signals_api.domain.entity.EmergencySignalType
import com.girrafeecstud.signals.signals_api.domain.entity.SignalSender
import com.google.gson.annotations.SerializedName

data class EmergencySignalDto(
    @SerializedName("signalId")
    val signalId: String,
    @SerializedName("signalStartTimestamp")
    var signalStartTimestamp: String,
    @SerializedName("latitude")
    var signalLatitude: Double,
    @SerializedName("longitude")
    var signalLongitude: Double,
    @SerializedName("sosSignalType")
    val emergencySignalType: EmergencySignalType,
    @SerializedName("signalTitle")
    val emergencySignalTitle: String,
    @SerializedName("signalDescription")
    val emergencySignalDescription: String?,
    @SerializedName("signalCaller")
    val signalSender: SignalSenderDto
)
