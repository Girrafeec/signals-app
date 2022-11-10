package com.girrafeecstud.society_safety_app.feature_auth.di

import com.girrafeecstud.society_safety_app.feature_auth.data.di.RegistrationApiModule
import com.girrafeecstud.society_safety_app.feature_auth.data.di.RegistrationDataSourceModule
import com.girrafeecstud.society_safety_app.feature_auth.data.di.RegistrationRepositoryModule
import com.girrafeecstud.society_safety_app.feature_auth.di.annotation.RegistrationScope
import com.girrafeecstud.society_safety_app.feature_auth.domain.di.RegistrationUseCaseModule
import com.girrafeecstud.society_safety_app.feature_auth.ui.RegistrationFragment
import dagger.Subcomponent

@RegistrationScope
@Subcomponent(
    modules = [
        RegistrationApiModule::class,
        RegistrationDataSourceModule::class,
        RegistrationRepositoryModule::class,
        RegistrationUseCaseModule::class,
        RegistrationModule::class
    ]
)
interface RegistrationComponent {

    fun inject(fragment: RegistrationFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build(): RegistrationComponent

    }

}