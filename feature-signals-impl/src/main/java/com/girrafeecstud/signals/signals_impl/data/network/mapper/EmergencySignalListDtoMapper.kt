/* Created by Girrafeec */

package com.girrafeecstud.signals.signals_impl.data.network.mapper

import com.girrafeecstud.signals.core_base.base.ListMapper
import com.girrafeecstud.signals.signals_api.domain.entity.EmergencySignal
import com.girrafeecstud.signals.signals_impl.data.network.dto.EmergencySignalDto
import com.girrafeecstud.signals.signals_impl.data.network.dto.SignalSenderDtoMapper
import javax.inject.Inject

class EmergencySignalListDtoMapper @Inject constructor(
    private val signalSenderMapper: SignalSenderDtoMapper
) : ListMapper<EmergencySignalDto, EmergencySignal> {

    override fun map(input: List<EmergencySignalDto>): List<EmergencySignal> =
        input.map {
            EmergencySignal(
                signalId = it.signalId,
                signalStartTimestamp = it.signalStartTimestamp,
                signalLatitude = it.signalLatitude,
                signalLongitude = it.signalLongitude,
                emergencySignalTitle = it.emergencySignalTitle,
                emergencySignalDescription = it.emergencySignalDescription,
                emergencySignalType = it.emergencySignalType,
                signalSender = signalSenderMapper.map(input = it.signalSender)
            )
        }
}