package com.girrafeecstud.society_safety_app.feature_map.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.girrafeecstud.location_tracker_api.domain.GetLastKnownLocationUseCase
import com.girrafeecstud.signals.rescuers_api.domain.Rescuer
import com.girrafeecstud.society_safety_app.core_base.domain.base.BusinessResult
import com.girrafeecstud.society_safety_app.core_base.presentation.base.BaseViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MapViewModel @Inject constructor(
    private val getLastKnownLocationUseCase: GetLastKnownLocationUseCase
) : BaseViewModel<MapUiState>() {

    override var _state: MutableStateFlow<MapUiState> = MutableStateFlow(MapUiState.Default)

    override val state: StateFlow<MapUiState> = _state.asStateFlow()

    init {
        getLastKnownLocationUseCase()
            .onEach { result ->
                Log.i("tag", "here")
                when (result) {
                    is BusinessResult.Error -> {}
                    is BusinessResult.Exception -> {}
                    is BusinessResult.Success -> {
                        // TODO NULL SAFETY!!
                        _state.update {
                            MapUiState.DrawCurrentLocation(location = result._data!!)
                        }
                    }
                }
            }
            .launchIn(viewModelScope)
    }

}