package com.girrafeecstud.society_safety_app.feature_auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.girrafeecstud.society_safety_app.core_base.domain.base.BusinessResult
import com.girrafeecstud.society_safety_app.core_base.presentation.base.BaseViewModel
import com.girrafeecstud.society_safety_app.feature_auth.domain.entity.UserLoginEntity
import com.girrafeecstud.society_safety_app.feature_auth.domain.usecase.UserLoginUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginUseCase: UserLoginUseCase
) : BaseViewModel() {
    // TODO Создавать здесь loginComponent?

    fun isUserPhoneNumberValid(userPhoneNmber: String) {

    }

    fun isUserPasswordValid(userPassword: String) {

    }

    fun login() {
        viewModelScope.launch {
            loginUseCase()
                .onStart {
                    setLoading()
                }
                .collect { result ->
                    when(result) {
                        is BusinessResult.Success -> {
                            setSuccessResult(data = null)
                        }
                        is BusinessResult.Error -> {

                        }
                        is BusinessResult.Exception -> {

                        }
                    }
                }
        }
    }


    override fun destroyComponent() {

    }

    override fun closeConnection() {

    }

}