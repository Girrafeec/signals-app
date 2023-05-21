/* Created by Girrafeec */

package com.girrafeecstud.signals.auth_api.data

import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import kotlinx.coroutines.flow.Flow

interface IAuthDataSource {

    suspend fun getUserAuthorizedStatus(): Boolean

    suspend fun setUserAuthorized()

    suspend fun setUserUnauthorized()

    suspend fun getUserToken(): Flow<BusinessResult<String>>

    suspend fun setUserToken(userToken: String)

    suspend fun clearUserToken()

}