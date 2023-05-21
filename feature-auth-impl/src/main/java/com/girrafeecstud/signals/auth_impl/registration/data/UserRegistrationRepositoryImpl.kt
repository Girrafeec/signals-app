package com.girrafeecstud.signals.auth_impl.registration.data

import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.auth_impl.registration.data.UserRegistrationDataSource
import com.girrafeecstud.signals.auth_impl.registration.domain.UserRegistrationEntity
import com.girrafeecstud.signals.auth_impl.registration.domain.UserRegistrationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRegistrationRepositoryImpl @Inject constructor(
    private val dataSource: UserRegistrationDataSource
): UserRegistrationRepository {

    override suspend fun registration(user: UserRegistrationEntity): Flow<BusinessResult<Nothing>> =
        dataSource.registration(user = user)
}
