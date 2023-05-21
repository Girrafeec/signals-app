package com.girrafeecstud.signals.auth_impl.login.data.network

import com.girrafeecstud.signals.auth_impl.login.data.network.UserLoginRequestDto
import com.girrafeecstud.signals.core_base.base.Mapper
import com.girrafeecstud.signals.auth_impl.login.domain.UserLoginEntity

class UserLoginEntityRequestDtoMapper: Mapper<UserLoginEntity, UserLoginRequestDto> {

    override fun map(input: UserLoginEntity): UserLoginRequestDto =
        with (input) {
            UserLoginRequestDto(
                userPhoneNumber = userPhoneNumber,
                userPassword = userPassword
            )
        }
}