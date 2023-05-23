package com.girrafeecstud.signals.signals_api.domain.entity

data class SignalSender(
    val signalSenderId: String,
    val signalSenderFirstName: String,
    val signalSenderLastName: String,
    val  signalSenderProfileImageUrl: String?,
    val signalSenderPhoneNumber: String? = null
)