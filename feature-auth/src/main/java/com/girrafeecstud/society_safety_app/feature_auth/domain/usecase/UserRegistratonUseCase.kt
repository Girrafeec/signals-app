package com.girrafeecstud.society_safety_app.feature_auth.domain.usecase

import com.girrafeecstud.society_safety_app.core_base.domain.base.BusinessResult
import com.girrafeecstud.society_safety_app.feature_auth.domain.entity.UserRegistrationEntity
import com.girrafeecstud.society_safety_app.feature_auth.domain.repository.UserRegistrationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRegistratonUseCase @Inject constructor(
    private val repository: UserRegistrationRepository
) {

    suspend operator fun invoke(user: UserRegistrationEntity): Flow<BusinessResult<Nothing>> =
        repository.registration(user = user)

}