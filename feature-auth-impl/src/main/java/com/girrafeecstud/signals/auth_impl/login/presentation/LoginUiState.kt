/* Created by Girrafeec */

package com.girrafeecstud.signals.auth_impl.login.presentation

import com.girrafeecstud.core_ui.presentation.UiState

data class LoginUiState(
    val isLoading: Boolean = false,
    val loginPassed: Boolean? = null,
    val loginError: Boolean? = null
) : UiState