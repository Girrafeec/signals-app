package com.girrafeecstud.signals.auth_impl.login.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.girrafeecstud.core_ui.presentation.BaseViewModel
import com.girrafeecstud.signals.auth_impl.login.domain.LoginUseCase
import com.girrafeecstud.signals.auth_impl.login.domain.UserLoginEntity
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : BaseViewModel<LoginUiState>() {

    override var _state: MutableStateFlow<LoginUiState> = MutableStateFlow(LoginUiState())
    override val state: StateFlow<LoginUiState> = _state.asStateFlow()

    fun isUserPhoneNumberValid(userPhoneNumber: String) {

    }

    fun isUserPasswordValid(userPassword: String) {

    }

    fun login(userPhoneNumber: String, userPassword: String) {
        viewModelScope.launch {
            loginUseCase(user = UserLoginEntity(userPhoneNumber = userPhoneNumber, userPassword = userPassword))
                .onStart {
                    _state.update { it.copy(isLoading = true) }
                }
                .collect { result ->
                    Log.i("login vm", "result: $result")
                    _state.update { it.copy(isLoading = false) }
                    when (result) {
                        is BusinessResult.Success -> {
                            _state.update { it.copy(loginPassed = true) }
                        }
                        is BusinessResult.Error -> {
                            _state.update { it.copy(loginError = true) }
                        }
                        is BusinessResult.Exception -> {
                            //TODO нужно ли отдельное состояние для exception?
                            _state.update { it.copy(loginError = true) }
                        }
                    }
                }
        }
    }
}