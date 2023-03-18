package com.girrafeecstud.signals.feature_auth.di

import com.girrafeecstud.signals.feature_auth.data.di.RegistrationApiModule
import com.girrafeecstud.signals.feature_auth.data.di.RegistrationDataSourceModule
import com.girrafeecstud.signals.feature_auth.data.di.RegistrationRepositoryModule
import com.girrafeecstud.signals.feature_auth.di.annotation.RegistrationScope
import com.girrafeecstud.signals.feature_auth.domain.di.RegistrationUseCaseModule
import com.girrafeecstud.signals.feature_auth.ui.RegistrationFragment
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