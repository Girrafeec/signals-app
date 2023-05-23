package com.girrafeecstud.signals.location_tracker_impl.data.network

import com.girrafeecstud.location_tracker_api.domain.entity.UserLocation
import com.girrafeecstud.signals.core_base.base.Mapper

class UserLocationEntityDtoMapper : Mapper<UserLocation, LocationsRequestDto> {

    override fun map(input: UserLocation): LocationsRequestDto =
        with(input) {
            LocationsRequestDto(
                this.latitude,
                this.longitude
            )
        }
}
