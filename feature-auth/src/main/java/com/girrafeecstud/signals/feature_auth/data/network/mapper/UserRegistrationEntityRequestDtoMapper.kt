package com.girrafeecstud.signals.feature_auth.data.network.mapper

import com.girrafeecstud.signals.core_base.base.Mapper
import com.girrafeecstud.signals.feature_auth.data.network.dto.UserRegistrationRequestDto
import com.girrafeecstud.signals.feature_auth.domain.entity.UserRegistrationEntity

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