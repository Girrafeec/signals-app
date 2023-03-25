package com.girrafeecstud.signals.signals_api.domain.entity

data class EmergencySignal(
    val signalId: String,
    val signalStartTimestamp: String,
    val signalLatitude: Double,
    val signalLongitude: Double,
    val emergencySignalType: EmergencySignalType,
    val emergencySignalTitle: String,
    val emergencySignalDescription: String,
    val signalSender: SignalSender
)
