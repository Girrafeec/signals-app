package com.girrafeecstud.signals.signal_details_impl.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.girrafeecstud.core_ui.presentation.BaseViewModel
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.signals.rescuer_mode_api.engine.IRescuerModeEngine
import com.girrafeecstud.signals.signals_api.domain.IGetSignalDetailsUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignalDetailsViewModel @Inject constructor(
    private val getSignalDetailsUseCase: IGetSignalDetailsUseCase,
    private val rescuerModeEngine: IRescuerModeEngine
) : BaseViewModel<SignalDetailsUiState>() {

    override var _state: MutableStateFlow<SignalDetailsUiState> = MutableStateFlow(SignalDetailsUiState.SignalDetailsLoading)
    override val state: StateFlow<SignalDetailsUiState> = _state.asStateFlow()

    init {
        Log.i("tag sos det", "vm init")
//        getSignalDetailsUseCase()
//            .onEach {
//
//            }
//            .launchIn(viewModelScope)
    }

    fun fetchSignalDetails(signalId: String) {
        viewModelScope.launch {
            getSignalDetailsUseCase(signalId = signalId)
                .onStart {
                    _state.update { SignalDetailsUiState.SignalDetailsLoading }
                }
                .onEach { result ->
                    when (result) {
                        is BusinessResult.Error -> {
                            _state.update { SignalDetailsUiState.SignalDetailsLoadingError }
                        }
                        is BusinessResult.Exception -> {
                            // TODO error!!
                        }
                        is BusinessResult.Success -> {
                            Log.i("tag sos det", "got success")
                            // TODO null safety
                            _state.update { SignalDetailsUiState.ShowSignalDetails(signal = result._data!!) }
                        }
                    }
                }
                .launchIn(viewModelScope)
        }
    }

    fun acceptSosSignal(signalId: String) {
        rescuerModeEngine.acceptSosSignal(signalId)
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("tag sos det", "vm cleared")
    }
}