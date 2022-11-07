package com.girrafeecstud.society_safety_app.core_base.presentation.base.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.girrafeecstud.society_safety_app.core_base.presentation.base.MainViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface BaseViewModelFactoryModule {

    @Binds
    fun bindMainViewModelFactory(impl: MainViewModelFactory): ViewModelProvider.Factory

}