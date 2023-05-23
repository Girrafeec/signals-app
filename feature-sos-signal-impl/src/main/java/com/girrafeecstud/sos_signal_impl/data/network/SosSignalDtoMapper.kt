/* Created by Girrafeec */

package com.girrafeecstud.sos_signal_impl.data.network

import com.girrafeecstud.signals.core_base.base.Mapper
import com.girrafeecstud.sos_signal_api.domain.entity.SosSignal

class SosSignalDtoMapper : Mapper<SosSignal, SosSignalDto> {

    override fun map(input: SosSignal): SosSignalDto =
        with(input) {
            SosSignalDto(
                this.signalTitle,
                this.signalDescription,
                this.signalType
            )
        }
}