package com.girrafeecstud.signals.auth_impl.registration.data

import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.auth_impl.registration.domain.UserRegistrationEntity
import kotlinx.coroutines.flow.Flow

interface UserRegistrationDataSource {

    suspend fun registration(user: UserRegistrationEntity): Flow<BusinessResult<Nothing>>

}
