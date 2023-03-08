package com.girrafeecstud.society_safety_app.feature_signals.presentation

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.girrafeecstud.society_safety_app.core_base.domain.base.BusinessResult
import com.girrafeecstud.society_safety_app.core_base.presentation.base.BaseViewModel
import com.girrafeecstud.society_safety_app.core_base.presentation.base.UiState
import com.girrafeecstud.society_safety_app.event_bus.AppEvent
import com.girrafeecstud.society_safety_app.event_bus.EventBus
import com.girrafeecstud.sos_signal_api.domain.SendSosSignalUseCase
import com.girrafeecstud.sos_signal_api.domain.entity.SosSignal
import com.girrafeecstud.sos_signal_api.domain.entity.SosSignalType
import com.girrafeecstud.sos_signal_api.engine.SosSignalEngine
import com.girrafeecstud.sos_signal_api.engine.SosSignalState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class SosSignalViewModel @Inject constructor(
    private val sosSignalEngine: SosSignalEngine
) : BaseViewModel<SosSignalUiState>() {

    override var _state: MutableStateFlow<SosSignalUiState> = MutableStateFlow(SosSignalUiState.ChooseSignalData)

    override val state: StateFlow<SosSignalUiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            sosSignalEngine.getSosSignalState()
                .onEach { state ->
                    when (state) {
                        is SosSignalState.SosSignalPreparing -> {
                            _state.update { SosSignalUiState.SignalCountDownDialog }
                        }
                        is SosSignalState.SosSignalSending -> {
                            _state.update { SosSignalUiState.SignalSending }
                        }
                        is SosSignalState.SosSignalUpdating -> {}
                        is SosSignalState.SosSignalSuccess -> {
                            Log.i("tag", "sent success")
                            _state.update { SosSignalUiState.SignalSent }
                        }
                        is SosSignalState.SosSignalError -> {
                            _state.update { SosSignalUiState.Error }
                        }
                        is SosSignalState.SosSignalDisabling -> {}
                        is SosSignalState.SosSignalDisabled -> {
                            _state.update { SosSignalUiState.ChooseSignalData }
                        }
                    }
                }
                .stateIn(viewModelScope)
        }
    }

    fun sendSosSignal(context: Context, sosSignal: SosSignal) {
        sosSignalEngine.enableSosSignal(context = context, sosSignal = sosSignal)
    }

}