package com.girrafeecstud.society_safety_app.feature_auth.di

import com.girrafeecstud.society_safety_app.feature_auth.data.di.AuthApiModule
import com.girrafeecstud.society_safety_app.feature_auth.data.di.AuthDataSourceModule
import com.girrafeecstud.society_safety_app.feature_auth.data.di.AuthRepositoryModule
import com.girrafeecstud.society_safety_app.feature_auth.di.annotation.AuthScope
import com.girrafeecstud.society_safety_app.feature_auth.domain.di.AuthUseCaseModule
import dagger.Component

@AuthScope
@Component(
    modules = [
        AuthUseCaseModule::class,
        AuthDataSourceModule::class,
        AuthRepositoryModule::class,
        AuthApiModule::class
    ]
)
interface AuthComponent {

}