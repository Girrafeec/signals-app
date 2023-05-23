package com.girrafeecstud.sos_signal_impl.data.network

import com.girrafeecstud.sos_signal_api.domain.entity.SosSignalType
import com.google.gson.annotations.SerializedName

data class SosSignalDto(
    @SerializedName("signalTitle")
    val signalTitle: String,
    @SerializedName("signalDescription")
    val signalDescription: String,
    @SerializedName("sosSignalType")
    val signalType: SosSignalType
)
