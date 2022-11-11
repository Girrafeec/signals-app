package com.girrafeecstud.society_safety_app.feature_map.presentation

import androidx.lifecycle.viewModelScope
import com.girrafeecstud.society_safety_app.core_base.presentation.base.BaseViewModel
import com.girrafeecstud.society_safety_app.core_preferences.data.repository.AuthSharedPreferencesRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val authSharedPreferencesRepository: AuthSharedPreferencesRepository
): BaseViewModel() {

    fun logOut() {
        viewModelScope.launch {
            async {
                authSharedPreferencesRepository.clearUserId()
            }
            async {
                authSharedPreferencesRepository.setUserUnauthorized()
            }
        }
    }

}