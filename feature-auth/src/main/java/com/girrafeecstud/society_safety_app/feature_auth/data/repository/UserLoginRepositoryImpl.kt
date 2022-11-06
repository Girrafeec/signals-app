package com.girrafeecstud.society_safety_app.feature_auth.data.repository

import com.girrafeecstud.society_safety_app.core_base.domain.base.BusinessResult
import com.girrafeecstud.society_safety_app.feature_auth.data.datasource.UserLoginDataSource
import com.girrafeecstud.society_safety_app.feature_auth.domain.entity.UserLoginEntity
import com.girrafeecstud.society_safety_app.feature_auth.domain.repository.UserLoginRepository
import kotlinx.coroutines.flow.*
import java.util.*
import javax.inject.Inject

class UserLoginRepositoryImpl @Inject constructor(
    private val dataSource: UserLoginDataSource
): UserLoginRepository {

     // TODO переписать нормальное получение результата?
    override suspend fun login(user: UserLoginEntity): Flow<BusinessResult<Nothing>> =
        flow {
            dataSource.login(user = user).collect { result ->
                when (result) {
                    is BusinessResult.Success -> {
                        // TODO добавить сохранение id (в будущем токена) в память и сохранять статус авторизации
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