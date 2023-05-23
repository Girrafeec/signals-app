/* Created by Girrafeec */

package com.girrafeecstud.signals.rescuers_impl.data.network

import com.girrafeecstud.signals.core_base.base.Mapper
import com.girrafeecstud.signals.rescuers_api.domain.Rescuer

class RescuerDtoEntityMapper : Mapper<RescuerDto, Rescuer> {

    override fun map(input: RescuerDto): Rescuer =
        with (input) {
            Rescuer(
                this.rescuerId,
                this.rescuerFirstName,
                this.rescuerLastName,
                this.rescuerPhoneNumber,
                this.rescuerProfileImageUrl,
                this.rescuerLocationLatitude,
                this.rescuerLocationLongitude
            )
        }
}