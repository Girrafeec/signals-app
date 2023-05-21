package com.girrafeecstud.signals.auth_impl.registration.di

import com.girrafeecstud.signals.auth_impl.registration.data.RegistrationApiModule
import com.girrafeecstud.signals.auth_impl.registration.data.RegistrationDataSourceModule
import com.girrafeecstud.signals.auth_impl.registration.data.RegistrationRepositoryModule
import com.girrafeecstud.signals.auth_impl.registration.ui.RegistrationFragment
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