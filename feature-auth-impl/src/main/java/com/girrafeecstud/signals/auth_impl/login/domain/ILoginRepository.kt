package com.girrafeecstud.signals.auth_impl.login.domain

import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.core_base.domain.base.EmptyResult
import kotlinx.coroutines.flow.Flow

interface ILoginRepository {

    fun login(user: UserLoginEntity): Flow<BusinessResult<EmptyResult>>

}