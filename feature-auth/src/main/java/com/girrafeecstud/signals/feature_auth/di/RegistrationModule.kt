package com.girrafeecstud.signals.feature_auth.di

import androidx.lifecycle.ViewModel
import com.girrafeecstud.signals.core_base.di.base.ViewModelKey
import com.girrafeecstud.signals.feature_auth.data.network.mapper.UserRegistrationEntityRequestDtoMapper
import com.girrafeecstud.signals.feature_auth.di.annotation.RegistrationScope
import com.girrafeecstud.signals.feature_auth.presentation.RegistrationViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [RegistrationModule.RegistrationBindModule::class])
class RegistrationModule {

    @Provides
    @RegistrationScope
    fun provideUserRegistrationEntityRequestDtoMapper() = UserRegistrationEntityRequestDtoMapper()

    @Module
    interface RegistrationBindModule {
        @Binds
        @RegistrationScope
        @ViewModelKey(RegistrationViewModel::class)
        @IntoMap
        fun bindRegistrationViewModel(impl: RegistrationViewModel): ViewModel
    }

}