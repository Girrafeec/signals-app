/* Created by Girrafeec */

package com.girrafeecstud.signals.auth_api.data

import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import kotlinx.coroutines.flow.Flow

interface IAuthDataSource {

    fun getUserAuthorizedStatus(): Boolean

    fun setUserAuthorized()

    fun setUserUnauthorized()

    fun getUserToken(): Flow<BusinessResult<String>>

    fun setUserToken(userToken: String)

    fun clearUserToken()

}