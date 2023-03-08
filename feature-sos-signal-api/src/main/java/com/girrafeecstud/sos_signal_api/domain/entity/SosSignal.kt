package com.girrafeecstud.sos_signal_api.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SosSignal(
    val signalTitle: String,
    val signalDescription: String,
    val signalType: SosSignalType
) : Parcelable
