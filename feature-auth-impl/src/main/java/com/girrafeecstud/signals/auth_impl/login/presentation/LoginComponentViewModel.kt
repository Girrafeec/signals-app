package com.girrafeecstud.signals.auth_impl.login.presentation

import com.girrafeecstud.core_ui.presentation.BaseComponentViewModel
import com.girrafeecstud.signals.auth_impl.di.AuthFeatureComponent
import com.girrafeecstud.signals.auth_impl.login.di.LoginComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginComponentViewModel: BaseComponentViewModel() {

    private var _loginComponent: LoginComponent? = null

    val  loginComponent get() = _loginComponent!!

    init {
        initComponent()
    }

    override fun initComponent() {
        _loginComponent = AuthFeatureComponent.authComponent.loginComponent().build()
    }

    override fun destroyComponent() {
        _loginComponent = null
    }

    override fun onCleared() {
        destroyComponent()
    }
}