package com.girrafeecstud.society_safety_app.feature_map.presentation

import androidx.lifecycle.viewModelScope
import com.girrafeecstud.society_safety_app.core_base.domain.base.BusinessResult
import com.girrafeecstud.society_safety_app.core_base.presentation.base.BaseViewModel
import com.girrafeecstud.sos_signal_api.domain.SendSosSignalUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

class SosSignalMapViewModel @Inject constructor(
    private val sendSosSignalUseCase: SendSosSignalUseCase
) : BaseViewModel() {

    val sos: Flow<BusinessResult<Nothing>> =
        sendSosSignalUseCase.result

}