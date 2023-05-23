/* Created by Girrafeec */

package com.girrafeecstud.signals.signals_impl.data.network.mapper

import com.girrafeecstud.signals.core_base.base.Mapper
import com.girrafeecstud.signals.signals_api.domain.entity.EmergencySignal
import com.girrafeecstud.signals.signals_impl.data.network.dto.EmergencySignalDto
import com.girrafeecstud.signals.signals_impl.data.network.dto.SignalSenderDtoMapper
import javax.inject.Inject

class EmergencySignalDtoMapper @Inject constructor(
    private val signalSenderMapper: SignalSenderDtoMapper
) : Mapper<EmergencySignalDto, EmergencySignal> {

    override fun map(input: EmergencySignalDto): EmergencySignal =
        with(input) {
            EmergencySignal(
                signalId = this.signalId,
                signalStartTimestamp = this.signalStartTimestamp,
                signalLatitude = this.signalLatitude,
                signalLongitude = this.signalLongitude,
                emergencySignalTitle = this.emergencySignalTitle,
                emergencySignalDescription = this.emergencySignalDescription,
                emergencySignalType = this.emergencySignalType,
                signalSender = signalSenderMapper.map(input = this.signalSender)
            )
        }
}