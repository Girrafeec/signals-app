package com.girrafeecstud.signals.feature_signals_screens.di

import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.girrafeecstud.signals.core_base.di.base.ViewModelKey
import com.girrafeecstud.signals.feature_signals_screens.di.annotation.SosSignalScope
import com.girrafeecstud.signals.feature_signals_screens.presentation.SosSignalViewModel
import com.girrafeecstud.signals.feature_signals_screens.ui.SosTypeViewHolder
import com.girrafeecstud.signals.feature_signals_screens.ui.SosTypesAdapter
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SosSignalModule {

    @Binds
    @SosSignalScope
    @ViewModelKey(SosSignalViewModel::class)
    @IntoMap
    fun bindSosSignalViewModel(impl: SosSignalViewModel): ViewModel

    @SosSignalScope
    @Binds
    fun bindSosTypeAdapter(impl: SosTypesAdapter): RecyclerView.Adapter<SosTypeViewHolder>

}