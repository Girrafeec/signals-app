package com.girrafeecstud.signals.feature_auth.domain.usecase

import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.feature_auth.domain.entity.UserLoginEntity
import com.girrafeecstud.signals.feature_auth.domain.repository.UserLoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserLoginUseCase @Inject constructor(
    private val repository: UserLoginRepository
) {

    suspend operator fun invoke(user: UserLoginEntity): Flow<BusinessResult<Nothing>> =
        repository.login(user = user)

}