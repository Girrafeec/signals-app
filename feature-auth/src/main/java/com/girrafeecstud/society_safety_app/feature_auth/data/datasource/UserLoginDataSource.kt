package com.girrafeecstud.society_safety_app.feature_auth.data.datasource

import com.girrafeecstud.society_safety_app.core_base.domain.base.BusinessResult
import com.girrafeecstud.society_safety_app.feature_auth.domain.entity.UserLoginEntity
import kotlinx.coroutines.flow.Flow
import java.util.*

interface UserLoginDataSource {

    suspend fun login(user: UserLoginEntity): Flow<BusinessResult<UUID>>

}