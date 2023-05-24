package com.girrafeecstud.signals.feature_map.presentation

import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.girrafeecstud.signals.rescuers_api.domain.IGetRescuersListUseCase
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.core_ui.presentation.BaseViewModel
import com.girrafeecstud.signals.rescuers_api.engine.RescuersEngine
import com.girrafeecstud.sos_signal_api.domain.entity.SosSignal
import com.girrafeecstud.sos_signal_api.engine.SosSignalEngine
import com.girrafeecstud.sos_signal_api.engine.SosSignalState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class SosMapViewModel @Inject constructor(
    private val sosSignalEngine: SosSignalEngine,
    private val getRescuersListUseCase: IGetRescuersListUseCase,
    private val rescuersEngine: RescuersEngine
) : BaseViewModel<SosMapUIState>() {

    override var _state: MutableStateFlow<SosMapUIState> = MutableStateFlow(SosMapUIState.SosSuccessSentMessage)
    override val state: StateFlow<SosMapUIState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getRescuersListUseCase()
                .onEach { result ->
                    Log.i("tag", "sos vm result $result")
                    when (result) {
                        is BusinessResult.Error -> {}
                        is BusinessResult.Exception -> {}
                        is BusinessResult.Success -> {
                            _state.update {
                                SosMapUIState.DrawRescuersLocations(rescuers = result._data)
                            }
                        }
                    }
                }
                .launchIn(viewModelScope)
        }

        sosSignalEngine.getSosSignalState()
            .onEach { state ->
                when (state) {
                    is SosSignalState.SosSignalPreparing -> {}
                    is SosSignalState.SosSignalSending -> {}
                    is SosSignalState.SosSignalUpdating -> {}
                    is SosSignalState.SosSignalSuccess -> {
                        SosMapUIState.SosSuccessSentMessage
                    }
                    is SosSignalState.SosSignalError -> {
                        SosMapUIState.SosErrorSentMessage
                    }
                    is SosSignalState.SosSignalDisabling -> {}
                    is SosSignalState.SosSignalDisabled -> {
                        _state.update { SosMapUIState.SosDisabled }
                    }
                }
            }
            .launchIn(viewModelScope)

        rescuersEngine.getState()
            .onEach { rescuersEngineState ->
                if (rescuersEngineState.rescuers != null) {
                    _state.update {
                        SosMapUIState.DrawRescuersLocations(rescuers = rescuersEngineState.rescuers)
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    fun updateSosSignal(context: Context, sosSignal: SosSignal) {
        sosSignalEngine.updateSosSignal(sosSignal = sosSignal)
    }

    fun disableSosSignal(context: Context) {
        sosSignalEngine.disableSosSignal()
    }

}