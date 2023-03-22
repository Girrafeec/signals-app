package com.girrafeecstud.signals.feature_auth.presentation

import com.girrafeecstud.core_ui.presentation.BaseViewModel
import com.girrafeecstud.signals.feature_auth.di.AuthComponent
import com.girrafeecstud.signals.feature_auth.di.LoginComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginComponentViewModel: com.girrafeecstud.core_ui.presentation.BaseViewModel<Any>() {

    override var _state: MutableStateFlow<Any>
        get() = TODO("Not yet implemented")
        set(value) {}
    override val state: StateFlow<Any>
        get() = TODO("Not yet implemented")
    private var _loginComponent: LoginComponent? = null

    val  loginComponent get() = _loginComponent!!

    init {
        _loginComponent = AuthComponent.authComponent.loginComponent().build()
    }

    override fun destroyComponent() {
        _loginComponent = null
    }

    fun destroy() {
        destroyComponent()
    }
}