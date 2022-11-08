package com.girrafeecstud.society_safety_app.feature_auth.presentation

import com.girrafeecstud.society_safety_app.core_base.presentation.base.BaseViewModel
import com.girrafeecstud.society_safety_app.core_network.data.di.CoreNetworkComponent
import com.girrafeecstud.society_safety_app.feature_auth.di.AuthComponent
import com.girrafeecstud.society_safety_app.feature_auth.di.DaggerAuthComponent
import com.girrafeecstud.society_safety_app.feature_auth.di.DaggerAuthComponent_AuthDependenciesComponent

class AuthComponentViewModel : BaseViewModel() {


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