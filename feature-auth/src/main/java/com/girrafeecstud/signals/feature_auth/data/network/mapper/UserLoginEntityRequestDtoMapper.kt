package com.girrafeecstud.signals.feature_auth.data.network.mapper

import com.girrafeecstud.signals.core_base.base.Mapper
import com.girrafeecstud.signals.feature_auth.data.network.dto.UserLoginRequestDto
import com.girrafeecstud.signals.feature_auth.domain.entity.UserLoginEntity

class UserLoginEntityRequestDtoMapper: Mapper<UserLoginEntity, UserLoginRequestDto> {

    override fun map(input: UserLoginEntity): UserLoginRequestDto =
        with (input) {
            UserLoginRequestDto(
                userPhoneNumber = userPhoneNumber,
                userPassword = userPassword
            )
        }
}