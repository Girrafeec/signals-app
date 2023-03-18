package com.girrafeecstud.signals.rescuers_list_impl.di

import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.girrafeecstud.signals.rescuers_list_api.presenation.RescuersListSharedStateEngine
import com.girrafeecstud.signals.rescuers_list_api.ui.RescuersListFragment
import com.girrafeecstud.signals.rescuers_list_impl.di.annotation.RescuersListFeatureScope
import com.girrafeecstud.signals.rescuers_list_impl.engine.RescuersListSharedStateEngineImpl
import com.girrafeecstud.signals.rescuers_list_impl.presentation.RescuersViewModel
import com.girrafeecstud.signals.rescuers_list_impl.ui.RescuerViewHolder
import com.girrafeecstud.signals.rescuers_list_impl.ui.RescuersAdapter
import com.girrafeecstud.signals.rescuers_list_impl.ui.RescuersListFragmentImpl
import com.girrafeecstud.signals.core_base.di.base.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes=[RescuersListFeatureModule.RescuersListFeatureBindModule::class])
class RescuersListFeatureModule {

    @Provides
    @RescuersListFeatureScope
    fun provideRescuersListFragmentImpl(): RescuersListFragment =
        RescuersListFragmentImpl()

    @Module
    interface RescuersListFeatureBindModule {
        @RescuersListFeatureScope
        @Binds
        @IntoMap
        @ViewModelKey(RescuersViewModel::class)
        fun bindRescuersViewModel(impl: RescuersViewModel): ViewModel

        @RescuersListFeatureScope
        @Binds
        fun bindRescuersListSharedStateEngineImpl(impl: RescuersListSharedStateEngineImpl): RescuersListSharedStateEngine

        @RescuersListFeatureScope
        @Binds
        fun bindRescuersAdapter(impl: RescuersAdapter): RecyclerView.Adapter<RescuerViewHolder>
    }
}