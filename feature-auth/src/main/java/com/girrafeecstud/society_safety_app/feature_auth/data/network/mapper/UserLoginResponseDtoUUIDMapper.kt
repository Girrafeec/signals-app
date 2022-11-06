package com.girrafeecstud.society_safety_app.feature_auth.data.network.mapper

import com.girrafeecstud.society_safety_app.core_base.base.Mapper
import com.girrafeecstud.society_safety_app.feature_auth.data.network.dto.UserLoginResponseDto
import java.util.*

class UserLoginResponseDtoUUIDMapper: Mapper<UserLoginResponseDto, UUID> {

    override fun map(input: UserLoginResponseDto): UUID =
        with (input) {
            UUID.fromString(userId)
        }
}