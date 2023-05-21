/* Created by Girrafeec */

package com.girrafeecstud.signals.auth_impl.auth_common.data

import com.girrafeecstud.signals.auth_api.data.IAuthDataSource
import com.girrafeecstud.signals.auth_api.data.IAuthRepository
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthSharedPreferencesRepository @Inject constructor(
    private val authDataSource: IAuthDataSource
) : IAuthRepository {

    override suspend fun getUserAuthorizedStatus(): Boolean =
        authDataSource.getUserAuthorizedStatus()

    override suspend fun setUserAuthorized() =
        authDataSource.setUserAuthorized()

    override suspend fun setUserUnauthorized() =
        authDataSource.setUserUnauthorized()

    override suspend fun getUserToken(): Flow<BusinessResult<String>> =
        authDataSource.getUserToken()

    override suspend fun setUserToken(userToken: String) =
        authDataSource.setUserToken(userToken = userToken)

    override suspend fun clearUserToken() =
        authDataSource.clearUserToken()
}