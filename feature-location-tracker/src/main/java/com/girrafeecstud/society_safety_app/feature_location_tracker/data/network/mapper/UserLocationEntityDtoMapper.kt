package com.girrafeecstud.society_safety_app.feature_location_tracker.data.network.mapper

import com.girrafeecstud.society_safety_app.core_base.base.Mapper
import com.girrafeecstud.society_safety_app.feature_location_tracker.data.network.dto.UserLocationRequestDto
import com.girrafeecstud.society_safety_app.feature_location_tracker.domain.entity.UserLocation

class UserLocationEntityDtoMapper : Mapper<UserLocation, UserLocationRequestDto> {

    override fun map(input: UserLocation): UserLocationRequestDto =
        with (input) {
            UserLocationRequestDto(
                latitude = latitude.toString(),
                longitude = longitude.toString()
            )
        }
}