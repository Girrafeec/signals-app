package com.girrafeecstud.sos_signal_api.domain.entity

data class SosSignal(
    val signalTitle: String,
    val signalDescription: String,
    val signalType: SosSignalType
)
