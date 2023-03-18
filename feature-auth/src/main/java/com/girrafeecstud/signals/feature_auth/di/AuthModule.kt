package com.girrafeecstud.signals.feature_auth.di

import dagger.Module

@Module(
    subcomponents = [
        LoginComponent::class,
        RegistrationComponent::class
    ]
)
class AuthModule {
}