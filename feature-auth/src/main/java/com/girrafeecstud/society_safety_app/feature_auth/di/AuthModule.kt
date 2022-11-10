package com.girrafeecstud.society_safety_app.feature_auth.di

import dagger.Module

@Module(
    subcomponents = [
        LoginComponent::class,
        RegistrationComponent::class
    ]
)
class AuthModule {
}