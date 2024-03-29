package com.girrafeecstud.signals.di

import androidx.lifecycle.ViewModel
import com.girrafeecstud.signals.core_base.di.base.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module(includes = [AppModule.AppBindModule::class])
class AppModule {

    @Module
    interface AppBindModule {

    }

}