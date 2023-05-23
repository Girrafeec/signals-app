/* Created by Girrafeec */

package com.girrafeecstud.signals.signals_impl.data.network.dto

import com.girrafeecstud.signals.core_base.base.Mapper
import com.girrafeecstud.signals.signals_api.domain.entity.SignalSender

class SignalSenderDtoMapper : Mapper<SignalSenderDto, SignalSender> {

    override fun map(input: SignalSenderDto): SignalSender =
        with (input) {
            SignalSender(
                this.signalSenderId,
                this.signalSenderFirstName,
                this.signalSenderLastName,
                this.signalSenderProfileImageUrl,
                this.signalSenderPhoneNumber
            )
        }
}