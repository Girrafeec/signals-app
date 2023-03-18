package com.girrafeecstud.signals.feature_auth.data.datasource

import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.feature_auth.domain.entity.UserRegistrationEntity
import kotlinx.coroutines.flow.Flow

interface UserRegistrationDataSource {

    suspend fun registration(user: UserRegistrationEntity): Flow<BusinessResult<Nothing>>

}
