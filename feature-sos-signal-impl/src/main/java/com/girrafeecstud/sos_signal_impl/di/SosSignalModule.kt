package com.girrafeecstud.sos_signal_impl.di

import com.girrafeecstud.sos_signal_api.domain.DisableSosSignalUseCase
import com.girrafeecstud.sos_signal_api.domain.SendSosSignalUseCase
import com.girrafeecstud.sos_signal_api.domain.UpdateSosSignalUseCase
import com.girrafeecstud.sos_signal_api.engine.SosSignalEngine
import com.girrafeecstud.sos_signal_impl.data.datasource.SosSignalDataSource
import com.girrafeecstud.sos_signal_impl.data.datasource.SosSignalDataSourceImpl
import com.girrafeecstud.sos_signal_impl.data.network.SosSignalDtoMapper
import com.girrafeecstud.sos_signal_impl.data.network.SosSignalsApi
import com.girrafeecstud.sos_signal_impl.data.repository.SosSignalRepositoryImpl
import com.girrafeecstud.sos_signal_impl.di.annotation.SosSignalScope
import com.girrafeecstud.sos_signal_impl.domain.repository.SosSignalRepository
import com.girrafeecstud.sos_signal_impl.domain.usecase.DisableSosSignalUseCaseImpl
import com.girrafeecstud.sos_signal_impl.domain.usecase.SendSosSignalUseCaseImpl
import com.girrafeecstud.sos_signal_impl.domain.usecase.UpdateSosSignalUseCaseImpl
import com.girrafeecstud.sos_signal_impl.engine.SosSignalEngineImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [SosSignalModule.SosSignalBindModule::class])
class SosSignalModule {

    @Provides
    @SosSignalScope
    fun provideSosSignalsApi(retrofit: Retrofit) = retrofit.create(SosSignalsApi::class.java)

    @Provides
    @SosSignalScope
    fun provideSosSignalDtoMapper() = SosSignalDtoMapper()

    @Module
    interface SosSignalBindModule {

        @Binds
        @SosSignalScope
        fun bindSosSignalDataSourceImpl(impl: SosSignalDataSourceImpl): SosSignalDataSource

        @Binds
        @SosSignalScope
        fun bindSosSignalRepositoryImpl(impl: SosSignalRepositoryImpl): SosSignalRepository

        @Binds
        @SosSignalScope
        fun bindSendSosSignalUseCaseImpl(impl: SendSosSignalUseCaseImpl): SendSosSignalUseCase

        @Binds
        @SosSignalScope
        fun bindUpdateSosSignalUseCaseImpl(impl: UpdateSosSignalUseCaseImpl): UpdateSosSignalUseCase

        @Binds
        @SosSignalScope
        fun bindDisableSosSignalUseCaseImpl(impl: DisableSosSignalUseCaseImpl): DisableSosSignalUseCase

        @Binds
        @SosSignalScope
        fun bindSosSignalEngineImpl(impl: SosSignalEngineImpl): SosSignalEngine
    }

}