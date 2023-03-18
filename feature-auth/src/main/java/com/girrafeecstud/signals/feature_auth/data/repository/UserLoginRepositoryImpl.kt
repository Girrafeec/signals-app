package com.girrafeecstud.signals.feature_auth.data.repository

import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.core_preferences.data.datasource.AuthSharedPreferencesDataSource
import com.girrafeecstud.signals.feature_auth.data.datasource.UserLoginDataSource
import com.girrafeecstud.signals.feature_auth.domain.entity.UserLoginEntity
import com.girrafeecstud.signals.feature_auth.domain.repository.UserLoginRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class UserLoginRepositoryImpl @Inject constructor(
    private val dataSource: UserLoginDataSource,
    private val authSharedPreferencesDataSource: AuthSharedPreferencesDataSource
): UserLoginRepository {

     // TODO переписать нормальное получение результата?
    override suspend fun login(user: UserLoginEntity): Flow<BusinessResult<Nothing>> =
        flow {
            dataSource.login(user = user).collect { result ->
                when (result) {
                    is BusinessResult.Success -> {
                        // Saving user Id (in future - token)
                        authSharedPreferencesDataSource.setUserId(userId = result._data.toString())
                        authSharedPreferencesDataSource.setUserAuthorized()
                        emit(BusinessResult.Success(_data = null))
                    }
                    is BusinessResult.Error -> {
                        emit(BusinessResult.Error(businessErrorType = result.businessErrorType))
                    }
                    is BusinessResult.Exception -> {
                        emit(BusinessResult.Exception(exceptionType = result.exceptionType))
                    }
                }
            }
        }

}