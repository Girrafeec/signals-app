package com.girrafeecstud.society_safety_app.feature_map.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.girrafeecstud.location_tracker_api.domain.GetLastKnownLocationUseCase
import com.girrafeecstud.location_tracker_api.domain.entity.UserLocation
import com.girrafeecstud.society_safety_app.core_base.domain.base.BusinessResult
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class SignalsMapViewModel @Inject constructor(
    private val getLastKnownLocationUseCase: GetLastKnownLocationUseCase
) : ViewModel() {

    val location: Flow<BusinessResult<UserLocation>> =
        getLastKnownLocationUseCase().shareIn(viewModelScope, started = SharingStarted.Lazily, replay = 1)


}