package com.girrafeecstud.signals.location_tracker_impl.di

import android.content.Context
import com.girrafeecstud.location_tracker_api.data.LocationTrackerClient
import com.girrafeecstud.location_tracker_api.domain.GetLastKnownLocationUseCase
import com.girrafeecstud.location_tracker_api.engine.LocationTrackerEngine
import com.girrafeecstud.signals.location_tracker_impl.di.annotation.LocationTrackerScope
import com.girrafeecstud.signals.location_tracker_impl.data.DefaultLocationTrackerClient
import com.girrafeecstud.location_tracker_api.data.LocationTrackerDataSource
import com.girrafeecstud.signals.location_tracker_impl.data.datasource.LocationTrackerDataSourceImpl
import com.girrafeecstud.signals.location_tracker_impl.data.repository.LocationTrackerRepositoryImpl
import com.girrafeecstud.signals.location_tracker_impl.domain.repository.LocationTrackerRepository
import com.girrafeecstud.signals.location_tracker_impl.domain.usecase.GetLastKnownLocationUseCaseImpl
import com.girrafeecstud.signals.location_tracker_impl.engine.LocationTrackerEngineImpl
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(
    includes = [LocationTrackerModule.LocationTrackerBindModule::class],
    subcomponents = [LocationTrackerReceiverComponent::class]
)
class LocationTrackerModule {

    @Provides
    @LocationTrackerScope
    fun provideFusedLocationClient(applicationContext: Context): FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(applicationContext)

    @Provides
    @LocationTrackerScope
    fun provideLocationTrackerEngineImpl(): LocationTrackerEngine =
        LocationTrackerEngineImpl()

    @Module
    interface LocationTrackerBindModule {
        @Binds
        @LocationTrackerScope
        fun bindDefaultLocationTrackerClient(impl: DefaultLocationTrackerClient): LocationTrackerClient

        @Binds
        @LocationTrackerScope
        fun bindLocationTrackerDataSourceImpl(impl: LocationTrackerDataSourceImpl): LocationTrackerDataSource

        @Binds
        @LocationTrackerScope
        fun bindLocationTrackerRepositoryImpl(impl: LocationTrackerRepositoryImpl): LocationTrackerRepository

        @Binds
        @LocationTrackerScope
        fun bindGetLastKnownLocationUseCaseImpl(impl: GetLastKnownLocationUseCaseImpl): GetLastKnownLocationUseCase

    }
}