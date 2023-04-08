/* Created by Girrafeec */

package com.girrafeecstud.signals.feature_signals_screens.presentation

import androidx.lifecycle.viewModelScope
import com.girrafeecstud.core_ui.presentation.BaseViewModel
import com.girrafeecstud.countdown_timer_api.engine.BaseCountDownTimerEngine
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class SosCountDownViewModel @Inject constructor(
    private val countDownTimerEngine: BaseCountDownTimerEngine
) : BaseViewModel<SosCountDownUiState>() {

    override var _state: MutableStateFlow<SosCountDownUiState> = MutableStateFlow(SosCountDownUiState())
    override val state: StateFlow<SosCountDownUiState> = _state.asStateFlow()

    init {
        countDownTimerEngine.getCountDownTimerState()
            .onEach { state ->
                if (state.isEnabled && state.millisLeft != null)
                    _state.update { it.copy(milliesLeft = state.millisLeft) }
                if (state.isFinished == true)
                    _state.update { it.copy(milliesLeft = null, sosTransmitting = true) }
            }
            .launchIn(viewModelScope)
    }
}