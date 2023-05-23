package com.girrafeecstud.sos_signal_api.domain.entity

enum class SosSignalType(val signalTypeName: String) {

    DEFAULT_SOS_SIGNAL("DEFAULT_SOS_SIGNAL"),
    HEART_ATTACK_SIGNAL("HEART_ATTACK_SIGNAL");

    companion object {
        private val map = SosSignalType.values().associateBy(SosSignalType::signalTypeName)
        fun fromExtension(extension: String) = map[extension.lowercase()] ?: DEFAULT_SOS_SIGNAL
    }

}