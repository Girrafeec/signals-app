package com.girrafeecstud.society_safety_app.core_preferences.data.repository

import com.girrafeecstud.society_safety_app.core_base.domain.base.BusinessErrorType
import com.girrafeecstud.society_safety_app.core_base.domain.base.BusinessResult
import com.girrafeecstud.society_safety_app.core_preferences.data.datasource.AuthSharedPreferencesDataSource
import javax.inject.Inject

class AuthSharedPreferencesRepository @Inject constructor(
    private val dataSource: AuthSharedPreferencesDataSource
) {

    suspend fun getUserAuthorizedStatus(): Boolean = dataSource.getUserAuthorizedStatus()

    suspend fun setUserAuthorized() = dataSource.setUserAuthorized()

    suspend fun setUserUnauthorized() = dataSource.setUserUnauthorized()

    suspend fun getUserId(): BusinessResult<String> {
        val result = dataSource.getUserId()

        if (result.equals(null)) {
            return BusinessResult.Error(businessErrorType = BusinessErrorType.USER_UNAUTHORIZED)
        }
        return BusinessResult.Success(_data = result)
    }

    suspend fun setUserId(userId: String) = dataSource.setUserId(userId = userId)

    suspend fun clearUserId() = dataSource.clearUserId()

}