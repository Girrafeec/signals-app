package com.girrafeecstud.signals.signals_api.domain.entity

data class EmergencySignal(
    val signalId: String,
    var signalStartTimestamp: String,
    var signalLatitude: Double,
    var signalLongitude: Double,
    val emergencySignalType: EmergencySignalType,
    val emergencySignalTitle: String,
    val emergencySignalDescription: String,
    val signalSender: SignalSender
)
