package com.girrafeecstud.sos_signal_api.engine

import com.girrafeecstud.society_safety_app.core_base.domain.base.BusinessResult
import com.girrafeecstud.sos_signal_api.domain.entity.SosSignal

sealed class SosSignalState {
    object SosSignalDisabled : SosSignalState()
    object SosSignalPreparing : SosSignalState()
    object SosSignalSending : SosSignalState()
    object SosSignalUpdating : SosSignalState()
    object SosSignalDisabling : SosSignalState()
    class SosSignalSuccess(
        val sosSignal: SosSignal
    ) : SosSignalState()
    // TODO error via business result.error?
    class SosSignalError(
        val sosSignal: SosSignal
    ) : SosSignalState()
}
