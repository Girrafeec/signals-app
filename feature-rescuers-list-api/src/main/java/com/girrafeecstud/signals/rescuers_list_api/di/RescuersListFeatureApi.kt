package com.girrafeecstud.signals.rescuers_list_api.di

import com.girrafeecstud.signals.rescuers_list_api.ui.RescuersListFragment
import com.girrafeecstud.signals.rescuers_list_api.presenation.RescuersListSharedStateEngine

interface RescuersListFeatureApi {

    fun getRescuersListSharedStateEngine(): RescuersListSharedStateEngine

    fun getRescuersListFragment(): RescuersListFragment

}