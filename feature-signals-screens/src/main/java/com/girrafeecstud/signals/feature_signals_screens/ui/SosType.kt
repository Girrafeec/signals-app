package com.girrafeecstud.signals.feature_signals_screens.ui

import com.girrafeecstud.sos_signal_api.domain.entity.SosSignalType

data class SosType(
    val typeId: Int,
    val typeImageResId: Int,
    val typeName: String,
    var isSelected: Boolean = false,
    val type: SosSignalType
)
