package com.girrafeecstud.signals.auth_impl.registration.data

import com.girrafeecstud.signals.core_base.base.Mapper
import com.girrafeecstud.signals.auth_impl.registration.domain.UserRegistrationEntity

class UserRegistrationEntityRequestDtoMapper: Mapper<UserRegistrationEntity, UserRegistrationRequestDto> {

    override fun map(input: UserRegistrationEntity): UserRegistrationRequestDto =
        with (input) {
            UserRegistrationRequestDto(
                userPhoneNumber = userPhoneNumber,
                userPassword =userPassword,
                userFirstName = userFirstName,
                userLastName = userLastName
            )
        }
}