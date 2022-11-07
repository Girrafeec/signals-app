package com.girrafeecstud.society_safety_app.feature_auth.presentation

import com.girrafeecstud.society_safety_app.core_base.presentation.base.BaseViewModel
import com.girrafeecstud.society_safety_app.feature_auth.di.AuthComponent
import com.girrafeecstud.society_safety_app.feature_auth.di.DaggerAuthComponent

class AuthComponentViewModel : BaseViewModel() {

    private var _authComponent: AuthComponent? = null

    val authComponent get() = _authComponent!!

    init {

        // TODO стала появляться ошибка тут
        _authComponent = DaggerAuthComponent
                .builder()
                .authDependencies(AuthComponent.AuthDependenciesComponent)
                .build()
    }

    override fun destroyComponent() {
        _authComponent = null
    }

}