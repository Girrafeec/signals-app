package com.girrafeecstud.signals.feature_auth.domain.repository

import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.feature_auth.domain.entity.UserLoginEntity
import kotlinx.coroutines.flow.Flow

interface UserLoginRepository {

    suspend fun login(user: UserLoginEntity): Flow<BusinessResult<Nothing>>

}