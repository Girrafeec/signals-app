package com.girrafeecstud.society_safety_app.di

import androidx.lifecycle.ViewModel
import com.girrafeecstud.society_safety_app.core_base.di.base.ViewModelKey
import com.girrafeecstud.society_safety_app.presentation.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [AppModule.AppBindModule::class])
class AppModule {

    @Module
    interface AppBindModule {

        @Binds
        @IntoMap
        @ApplicationScope
        @ViewModelKey(MainViewModel::class)
        fun bindMainViewModel(impl: MainViewModel): ViewModel

    }

}