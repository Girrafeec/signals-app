package com.girrafeecstud.signals.core_preferences.data.repository

import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.core_preferences.data.datasource.AuthSharedPreferencesDataSource
import javax.inject.Inject

class AuthSharedPreferencesRepository @Inject constructor(
    private val dataSource: AuthSharedPreferencesDataSource
) {

    suspend fun getUserAuthorizedStatus(): Boolean = dataSource.getUserAuthorizedStatus()

    suspend fun setUserAuthorized() = dataSource.setUserAuthorized()

    suspend fun setUserUnauthorized() = dataSource.setUserUnauthorized()

    suspend fun getUserId(): BusinessResult<String> {
        return dataSource.getUserId()
    }

    suspend fun setUserId(userId: String) = dataSource.setUserId(userId = userId)

    suspend fun clearUserId() = dataSource.clearUserId()

}