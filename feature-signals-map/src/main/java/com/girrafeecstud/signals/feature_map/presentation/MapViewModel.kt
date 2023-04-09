package com.girrafeecstud.signals.feature_map.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.girrafeecstud.location_tracker_api.domain.GetLastKnownLocationUseCase
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import com.girrafeecstud.core_ui.presentation.BaseViewModel
import com.girrafeecstud.signals.feature_map.data.MapSharedPreferencesDataSource
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.osmdroid.api.IGeoPoint
import org.osmdroid.util.GeoPoint
import javax.inject.Inject

class MapViewModel @Inject constructor(
    private val getLastKnownLocationUseCase: GetLastKnownLocationUseCase,
    private val mapDataSource: MapSharedPreferencesDataSource
) : BaseViewModel<MapUiState>() {

    override var _state: MutableStateFlow<MapUiState> = MutableStateFlow(MapUiState.Default)

    override val state: StateFlow<MapUiState> = _state.asStateFlow()

    var mapCenterPoint: GeoPoint? = null

    var mapZoom: Double? = null

    init {
        viewModelScope.launch {
            mapCenterPoint = mapDataSource.getMapLastCenterPoint()
        }

        viewModelScope.launch {
            mapZoom = mapDataSource.getMapLastZoom()
        }

        getLastKnownLocationUseCase()
            .onEach { result ->
                Log.i("tag", "here")
                when (result) {
                    is BusinessResult.Error -> {}
                    is BusinessResult.Exception -> {}
                    is BusinessResult.Success -> {
                        Log.i("tag location vm", "success")
                        // TODO NULL SAFETY!!
                        _state.update {
                            MapUiState.DrawCurrentLocation(location = result._data!!)
                        }
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    fun saveLastMapUtils(centerPoint: IGeoPoint, zoom: Double) {
        viewModelScope.launch {
            viewModelScope.async {
                mapDataSource.setMapLastCenterPoint(geoPoint = centerPoint)
            }
            viewModelScope.async {
                mapDataSource.setMapLastZoom(zoom = zoom)
            }.await()
        }
    }

}