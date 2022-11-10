package com.girrafeecstud.society_safety_app.core_base.presentation.base.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.girrafeecstud.society_safety_app.core_base.di.base.ViewModelKey
import com.girrafeecstud.society_safety_app.core_base.presentation.base.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
interface BaseViewModelFactoryModule {

    @Singleton
    @Binds
    abstract fun bindMainViewModelFactory(impl: MainViewModelFactory): ViewModelProvider.Factory

}