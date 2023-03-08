package com.girrafeecstud.society_safety_app.feature_auth.presentation

import com.girrafeecstud.society_safety_app.core_base.presentation.base.BaseViewModel
import com.girrafeecstud.society_safety_app.feature_auth.di.AuthComponent
import com.girrafeecstud.society_safety_app.feature_auth.di.RegistrationComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RegistrationComponentViewModel: BaseViewModel<Any>() {

    override var _state: MutableStateFlow<Any>
        get() = TODO("Not yet implemented")
        set(value) {}
    override val state: StateFlow<Any>
        get() = TODO("Not yet implemented")
    private var _registrationComponent: RegistrationComponent? = null

    val registrationComponent get() = _registrationComponent!!

    init {
        _registrationComponent = AuthComponent
            .authComponent
            .registrationComponent()
            .build()
    }

    override fun destroyComponent() {
        _registrationComponent
    }

    fun destroy() {
        destroyComponent()
    }
}