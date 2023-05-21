package com.girrafeecstud.signals.auth_impl.login.domain

import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.core_base.domain.base.EmptyResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: ILoginRepository
) {

    operator fun invoke(user: UserLoginEntity): Flow<BusinessResult<EmptyResult>> =
        repository.login(user = user)

}