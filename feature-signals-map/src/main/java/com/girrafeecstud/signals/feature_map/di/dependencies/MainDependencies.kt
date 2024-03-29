package com.girrafeecstud.signals.feature_map.di.dependencies

import android.content.Context
import com.girrafeecstud.location_tracker_api.domain.GetLastKnownLocationUseCase
import com.girrafeecstud.location_tracker_api.engine.LocationTrackerEngine
import com.girrafeecstud.on_board.data.OnBoardSharedPreferencesDataSource
import com.girrafeecstud.push_notifications_api.engine.INotificationTokensEngine
import com.girrafeecstud.signals.rescuer_details_api.ui.BaseRescuerDetailsFragment
import com.girrafeecstud.signals.rescuers_api.domain.IGetRescuersListUseCase
import com.girrafeecstud.signals.rescuers_list_api.presenation.RescuersListSharedStateEngine
import com.girrafeecstud.signals.rescuers_list_api.ui.RescuersListFragment
import com.girrafeecstud.signals.core_preferences.data.repository.AuthSharedPreferencesRepository
import com.girrafeecstud.signals.event_bus.EventBus
import com.girrafeecstud.signals.rescuer_mode_api.engine.IRescuerModeEngine
import com.girrafeecstud.signals.rescuers_api.engine.RescuersEngine
import com.girrafeecstud.signals.signal_details_api.ui.BaseSignalDetailsFragment
import com.girrafeecstud.signals.signals_api.domain.GetSignalsListUseCase
import com.girrafeecstud.signals.signals_api.engine.SignalsEngine
import com.girrafeecstud.sos_signal_api.engine.SosSignalEngine

interface MainDependencies {

    fun getApplicationContext(): Context

    fun authSharedPreferencesRepository(): AuthSharedPreferencesRepository

    fun getLastKnownLocationUseCase(): GetLastKnownLocationUseCase

    fun getLocationTrackerEngine(): LocationTrackerEngine

    fun getEventBus(): EventBus

    fun getSosSignalEngine(): SosSignalEngine

    fun getGetRescuersListUseCase(): IGetRescuersListUseCase

    fun getRescuersListFragment(): RescuersListFragment

    fun getRescuersListSharedStateEngine(): RescuersListSharedStateEngine

    fun getRescuerDetailsFragment(): BaseRescuerDetailsFragment

    fun getGetSignalsListUseCase(): GetSignalsListUseCase

    fun getSignalDetailsFragment(): BaseSignalDetailsFragment

    fun getOnBoardSharedPreferencesDataSource(): OnBoardSharedPreferencesDataSource

    fun getNotificationTokensEngine(): INotificationTokensEngine

    fun getSignalsEngine(): SignalsEngine

    fun getRescuersEngine(): RescuersEngine

    fun getRescuerModeEngine(): IRescuerModeEngine
}