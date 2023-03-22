package com.girrafeecstud.signals.feature_auth.presentation

import com.girrafeecstud.core_ui.presentation.BaseViewModel
import com.girrafeecstud.signals.feature_auth.domain.usecase.UserLoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginUseCase: UserLoginUseCase
) : com.girrafeecstud.core_ui.presentation.BaseViewModel<Any>() {

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