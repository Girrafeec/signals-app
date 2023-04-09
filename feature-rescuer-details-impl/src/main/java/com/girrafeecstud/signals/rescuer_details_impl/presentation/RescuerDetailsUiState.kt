/* Created by Girrafeec */

package com.girrafeecstud.signals.rescuer_details_impl.presentation

import com.girrafeecstud.core_ui.presentation.UiState
import com.girrafeecstud.signals.rescuers_api.domain.Rescuer

data class RescuerDetailsUiState(
    val isLoading: Boolean = false,
    val rescuerDetails: Rescuer? = null,
    val error: String? = null
) : UiState