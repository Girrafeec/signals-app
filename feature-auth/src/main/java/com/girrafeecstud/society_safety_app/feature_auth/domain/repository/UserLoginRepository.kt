package com.girrafeecstud.society_safety_app.feature_auth.domain.repository

import com.girrafeecstud.society_safety_app.core_base.domain.base.BusinessResult
import com.girrafeecstud.society_safety_app.feature_auth.domain.entity.UserLoginEntity
import kotlinx.coroutines.flow.Flow

interface UserLoginRepository {

    suspend fun login(user: UserLoginEntity): Flow<BusinessResult<Nothing>>

}