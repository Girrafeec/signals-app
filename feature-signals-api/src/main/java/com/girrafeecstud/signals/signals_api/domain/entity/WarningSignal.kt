package com.girrafeecstud.signals.signals_api.domain.entity

import java.time.LocalDateTime

data class WarningSignal(
    override val signalId: String,
    override val userId: String,
    override val signalStartTimestamp: LocalDateTime
) : Signal