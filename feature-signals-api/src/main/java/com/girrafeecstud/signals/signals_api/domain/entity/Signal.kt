package com.girrafeecstud.signals.signals_api.domain.entity

import java.time.LocalDateTime

interface Signal {
    val signalId: String
    val userId: String
    val signalStartTimestamp: LocalDateTime
}
