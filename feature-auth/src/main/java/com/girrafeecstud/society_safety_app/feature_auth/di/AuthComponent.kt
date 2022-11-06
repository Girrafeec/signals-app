package com.girrafeecstud.society_safety_app.feature_auth.di

import com.girrafeecstud.society_safety_app.feature_auth.di.annotation.AuthScope
import dagger.Component

@AuthScope
@Component(
    modules = [
    ]
)
interface AuthComponent {

    @Component.Builder
    interface Builder {
        fun build(): AuthComponent
    }

}