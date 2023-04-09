/* Created by Girrafeec */

package com.girrafeecstud.signals.feature_map.presentation

import com.girrafeecstud.core_ui.presentation.UiState
import com.girrafeecstud.signals.rescuers_api.domain.Rescuer

data class SosMapSharedUiState(
    val rescuerDetails: Rescuer? = null
) : UiState