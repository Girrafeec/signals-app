package com.girrafeecstud.signals.feature_auth.data.datasource

import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.feature_auth.domain.entity.UserLoginEntity
import kotlinx.coroutines.flow.Flow
import java.util.*

interface UserLoginDataSource {

    suspend fun login(user: UserLoginEntity): Flow<BusinessResult<UUID>>

}