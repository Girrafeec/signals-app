package com.girrafeecstud.society_safety_app.feature_auth.data.datasource

import com.girrafeecstud.society_safety_app.core_base.domain.base.BusinessResult
import com.girrafeecstud.society_safety_app.feature_auth.domain.entity.UserRegistrationEntity
import kotlinx.coroutines.flow.Flow

interface UserRegistrationDataSource {

    suspend fun registration(user: UserRegistrationEntity): Flow<BusinessResult<Nothing>>

}
