package com.girrafeecstud.signals.feature_map.di.dependencies

import com.girrafeecstud.location_tracker_api.domain.GetLastKnownLocationUseCase
import com.girrafeecstud.location_tracker_api.engine.LocationTrackerEngine
import com.girrafeecstud.signals.rescuer_details_api.ui.RescuerDetailsFragmentDialog
import com.girrafeecstud.signals.rescuers_api.domain.GetRescuersListUseCase
import com.girrafeecstud.signals.rescuers_list_api.presenation.RescuersListSharedStateEngine
import com.girrafeecstud.signals.rescuers_list_api.ui.RescuersListFragment
import com.girrafeecstud.signals.core_preferences.data.repository.AuthSharedPreferencesRepository
import com.girrafeecstud.signals.event_bus.EventBus
import com.girrafeecstud.sos_signal_api.engine.SosSignalEngine

interface MainDependencies {

    fun authSharedPreferencesRepository(): AuthSharedPreferencesRepository

    fun getLastKnownLocationUseCase(): GetLastKnownLocationUseCase

    fun getLocationTrackerEngine(): LocationTrackerEngine

    fun getEventBus(): EventBus

    fun getSosSignalEngine(): SosSignalEngine

    fun getGetRescuersListUseCase(): GetRescuersListUseCase

    fun getRescuersListFragment(): RescuersListFragment

    fun getRescuersListSharedStateEngine(): RescuersListSharedStateEngine

    fun getRescuerDetailsFragmentDialog(): RescuerDetailsFragmentDialog
}