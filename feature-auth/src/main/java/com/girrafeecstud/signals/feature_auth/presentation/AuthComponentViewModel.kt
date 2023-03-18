package com.girrafeecstud.signals.feature_auth.presentation

import com.girrafeecstud.signals.core_base.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthComponentViewModel : BaseViewModel<Any>() {
    override var _state: MutableStateFlow<Any>
        get() = TODO("Not yet implemented")
        set(value) {}
    override val state: StateFlow<Any>
        get() = TODO("Not yet implemented")


    //TODO решить нужна ли эта вьюмодель

//    private var _authComponent: AuthComponent? = null
//
//    val authComponent get() = _authComponent!!
//
//    init {
//        AuthComponent.init(
//            DaggerAuthComponent_AuthDependenciesComponent
//                .builder()
//                .coreNetworkApi(CoreNetworkComponent.coreNetworkComponent)
////                .coreBaseApi(CoreBaseComponent.coreBaseComponent)
//                .build()
//        )
//        _authComponent = AuthComponent.authComponent
//    }
//
//    override fun destroyComponent() {
//        _authComponent = null
//        AuthComponent.reset()
//    }
//
//    fun destroy() {
//        destroyComponent()
//    }

}