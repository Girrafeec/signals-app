package com.girrafeecstud.society_safety_app.feature_auth.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.girrafeecstud.society_safety_app.core_base.domain.base.BusinessResult
import com.girrafeecstud.society_safety_app.core_base.presentation.base.BaseViewModel
import com.girrafeecstud.society_safety_app.feature_auth.domain.entity.UserLoginEntity
import com.girrafeecstud.society_safety_app.feature_auth.domain.usecase.UserLoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginUseCase: UserLoginUseCase
) : BaseViewModel<Any>() {

    override var _state: MutableStateFlow<Any>
        get() = TODO("Not yet implemented")
        set(value) {}
    override val state: StateFlow<Any>
        get() = TODO("Not yet implemented")

    fun isUserPhoneNumberValid(userPhoneNumber: String) {

    }

    fun isUserPasswordValid(userPassword: String) {

    }

    fun login(userPhoneNumber: String, userPassword: String,) {
//        viewModelScope.launch {
//            loginUseCase(user = UserLoginEntity(userPhoneNumber = userPhoneNumber, userPassword = userPassword))
//                .onStart {
//                    setLoading()
//                }
//                .collect { result ->
//                    when (result) {
//                        is BusinessResult.Success -> {
//                            setSuccess(data = null)
//                        }
//                        is BusinessResult.Error -> {
//                            setError(data = null)
//                        }
//                        is BusinessResult.Exception -> {
//                            //TODO нужно ли отдельное состояние для exception?
//                            setError(data = null)
//                        }
//                    }
//                }
//        }
    }
}