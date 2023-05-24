package com.girrafeecstud.signals.rescuer_mode_impl.di

import com.girrafeecstud.signals.rescuer_mode_api.engine.IRescuerModeEngine
import com.girrafeecstud.signals.rescuer_mode_impl.data.IRescuerModeDataSource
import com.girrafeecstud.signals.rescuer_mode_impl.data.RescuerModeDataSource
import com.girrafeecstud.signals.rescuer_mode_impl.data.RescuerModeRepository
import com.girrafeecstud.signals.rescuer_mode_impl.data.network.RescuersModeApi
import com.girrafeecstud.signals.rescuer_mode_impl.domain.AcceptSosSignalUseCase
import com.girrafeecstud.signals.rescuer_mode_impl.domain.IRescuerModeRepository
import com.girrafeecstud.signals.rescuer_mode_impl.domain.RejectSosSignalUseCase
import com.girrafeecstud.signals.rescuer_mode_impl.engine.RescuerModeEngine
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [RescuerModeFeatureModule.RescuerModeFeatureBindModule::class])
class RescuerModeFeatureModule {

    @RescuerModeFeatureScope
    @Provides
    fun provideRescuersModeApi(retrofit: Retrofit) =
        retrofit.create(RescuersModeApi::class.java)

    @RescuerModeFeatureScope
    @Provides
    fun provideAcceptSosSignalUseCase(repository: IRescuerModeRepository) =
        AcceptSosSignalUseCase(repository = repository)

    @RescuerModeFeatureScope
    @Provides
    fun provideRejectSosSignalUseCase(repository: IRescuerModeRepository) =
        RejectSosSignalUseCase(repository = repository)

    @Module
    interface RescuerModeFeatureBindModule {

        @Binds
        @RescuerModeFeatureScope
        fun bindRescuerModeDataSource(impl: RescuerModeDataSource): IRescuerModeDataSource

        @Binds
        @RescuerModeFeatureScope
        fun bindRescuerModeRepository(impl: RescuerModeRepository): IRescuerModeRepository

        @Binds
        @RescuerModeFeatureScope
        fun bindRescuerModeEngine(impl: RescuerModeEngine): IRescuerModeEngine

    }

}
