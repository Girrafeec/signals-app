package com.girrafeecstud.society_safety_app.feature_auth.data.repository

import com.girrafeecstud.society_safety_app.core_base.domain.base.BusinessResult
import com.girrafeecstud.society_safety_app.feature_auth.data.datasource.UserRegistrationDataSource
import com.girrafeecstud.society_safety_app.feature_auth.domain.entity.UserRegistrationEntity
import com.girrafeecstud.society_safety_app.feature_auth.domain.repository.UserRegistrationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRegistrationRepositoryImpl @Inject constructor(
    private val dataSource: UserRegistrationDataSource
): UserRegistrationRepository {

    override suspend fun registration(user: UserRegistrationEntity): Flow<BusinessResult<Nothing>> =
        dataSource.registration(user = user)
}
