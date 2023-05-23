package com.girrafeecstud.signals.signals_impl.data.network.dto

import com.google.gson.annotations.SerializedName

data class SignalSenderDto(
    @SerializedName("signalCallerId")
    val signalSenderId: String,
    @SerializedName("signalCallerFirstName")
    val signalSenderFirstName: String,
    @SerializedName("signalCallerLastName")
    val signalSenderLastName: String,
    @SerializedName("signalCallerProfileImageUrl")
    val  signalSenderProfileImageUrl: String?,
    @SerializedName("signalCallerPhoneNumber")
    val signalSenderPhoneNumber: String? = null
)
