package com.girrafeecstud.society_safety_app.di

import com.girrafeecstud.society_safety_app.feature_auth.di.provider.AuthComponentProvider
import dagger.Component

@ApplicationScope
@Component(
    modules = [],
    dependencies = [
        AppDependencies::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        fun appDependencies(appDependencies: AppDependencies): Builder

        fun build(): AppComponent
    }

}