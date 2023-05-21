package com.girrafeecstud.signals.auth_impl.login.data.datasource

import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.auth_impl.login.domain.UserLoginEntity
import kotlinx.coroutines.flow.Flow
import java.util.*

interface ILoginDataSource {

    fun login(user: UserLoginEntity): Flow<BusinessResult<String>>

}