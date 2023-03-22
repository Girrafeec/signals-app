package com.girrafeecstud.signals.feature_signals_screens.presentation

import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.girrafeecstud.core_ui.presentation.BaseViewModel
import com.girrafeecstud.sos_signal_api.domain.entity.SosSignal
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