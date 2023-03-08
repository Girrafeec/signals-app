package com.girrafeecstud.society_safety_app.feature_auth.presentation

import com.girrafeecstud.society_safety_app.core_base.presentation.base.BaseViewModel
import com.girrafeecstud.society_safety_app.feature_auth.di.AuthComponent
import com.girrafeecstud.society_safety_app.feature_auth.di.LoginComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginComponentViewModel: BaseViewModel<Any>() {

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