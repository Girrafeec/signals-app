package com.girrafeecstud.signals.auth_impl.registration.domain

import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRegistratonUseCase @Inject constructor(
    private val repository: UserRegistrationRepository
) {

    suspend operator fun invoke(user: UserRegistrationEntity): Flow<BusinessResult<Nothing>> =
        repository.registration(user = user)

}