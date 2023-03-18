package com.girrafeecstud.signals.core_base.presentation.base.di

import androidx.lifecycle.ViewModelProvider
import com.girrafeecstud.signals.core_base.presentation.base.*
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface BaseViewModelFactoryModule {

    @Singleton
    @Binds
    abstract fun bindMainViewModelFactory(impl: MainViewModelFactory): ViewModelProvider.Factory

}