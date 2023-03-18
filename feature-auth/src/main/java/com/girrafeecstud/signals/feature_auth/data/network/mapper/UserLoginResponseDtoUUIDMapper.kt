package com.girrafeecstud.signals.feature_auth.data.network.mapper

import com.girrafeecstud.signals.core_base.base.Mapper
import com.girrafeecstud.signals.feature_auth.data.network.dto.UserLoginResponseDto
import java.util.*

class UserLoginResponseDtoUUIDMapper: Mapper<UserLoginResponseDto, UUID> {

    override fun map(input: UserLoginResponseDto): UUID =
        with (input) {
            UUID.fromString(userId)
        }
}