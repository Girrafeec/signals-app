package com.girrafeecstud.society_safety_app.feature_auth.di.dependencies

import androidx.lifecycle.ViewModelProvider
import retrofit2.Retrofit

interface AuthDependencies {

    fun retroft(): Retrofit

//    fun mainViewModelFactory(): ViewModelProvider.Factory

}