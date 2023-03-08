package com.girrafeecstud.society_safety_app.feature_map.presentation

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.girrafeecstud.society_safety_app.core_base.domain.base.BusinessResult
import com.girrafeecstud.society_safety_app.core_base.presentation.base.BaseViewModel
import com.girrafeecstud.society_safety_app.event_bus.EventBus
import com.girrafeecstud.sos_signal_api.domain.SendSosSignalUseCase
import com.girrafeecstud.sos_signal_api.domain.entity.SosSignal
import com.girrafeecstud.sos_signal_api.engine.SosSignalEngine
import com.girrafeecstud.sos_signal_api.engine.SosSignalState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class SosSignalMapViewModel @Inject constructor(
    private val sosSignalEngine: SosSignalEngine
) : BaseViewModel<SosSignalMapUIState>() {

    override var _state: MutableStateFlow<SosSignalMapUIState> = MutableStateFlow(SosSignalMapUIState.SosSuccessSentMessage)
    override val state: StateFlow<SosSignalMapUIState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            sosSignalEngine.getSosSignalState()
                .onEach { state ->
                    when (state) {
                        is SosSignalState.SosSignalPreparing -> {}
                        is SosSignalState.SosSignalSending -> {}
                        is SosSignalState.SosSignalUpdating -> {}
                        is SosSignalState.SosSignalSuccess -> {
                            SosSignalMapUIState.SosSuccessSentMessage
                        }
                        is SosSignalState.SosSignalError -> {
                            SosSignalMapUIState.SosErrorSentMessage
                        }
                        is SosSignalState.SosSignalDisabling -> {}
                        is SosSignalState.SosSignalDisabled -> {
                            _state.update { SosSignalMapUIState.SosSignalDisabled }
                        }
                    }
                }
                .launchIn(viewModelScope)
        }
    }

    fun updateSosSignal(context: Context, sosSignal: SosSignal) {
        sosSignalEngine.updateSosSignal(context = context, sosSignal = sosSignal)
    }

    fun disableSosSignal(context: Context) {
        sosSignalEngine.disableSosSignal(context = context)
    }

}