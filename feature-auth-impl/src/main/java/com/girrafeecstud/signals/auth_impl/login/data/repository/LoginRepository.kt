package com.girrafeecstud.signals.auth_impl.login.data.repository

import com.girrafeecstud.signals.auth_api.data.IAuthDataSource
import com.girrafeecstud.signals.auth_impl.login.data.datasource.ILoginDataSource
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.auth_impl.login.domain.UserLoginEntity
import com.girrafeecstud.signals.auth_impl.login.domain.ILoginRepository
import com.girrafeecstud.signals.core_base.domain.base.EmptyResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val loginDataSource: ILoginDataSource,
    private val authDataSource: IAuthDataSource
): ILoginRepository {

    private val loginRepositoryScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun login(user: UserLoginEntity): Flow<BusinessResult<EmptyResult>> =
        loginDataSource.login(user = user).flatMapLatest { loginResult ->
            when (loginResult) {
                is BusinessResult.Success -> {
                    loginRepositoryScope.async {
                        authDataSource.setUserAuthorized()
                    }
                    loginRepositoryScope.async {
                        authDataSource.setUserToken(userToken = loginResult._data!!)
                    }.await()
                    flowOf(BusinessResult.Success(EmptyResult))
                }
                is BusinessResult.Error -> flowOf(loginResult)
                is BusinessResult.Exception -> flowOf(loginResult)
            }
        }

}