package com.girrafeecstud.signals.auth_impl.registration.domain

import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import kotlinx.coroutines.flow.Flow

interface UserRegistrationRepository {

    suspend fun registration(user: UserRegistrationEntity): Flow<BusinessResult<Nothing>>

}