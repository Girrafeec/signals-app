package com.girrafeecstud.society_safety_app.feature_map.presentation

import androidx.lifecycle.viewModelScope
import com.girrafeecstud.society_safety_app.core_base.presentation.base.BaseViewModel
import com.girrafeecstud.society_safety_app.core_preferences.data.repository.AuthSharedPreferencesRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val authSharedPreferencesRepository: AuthSharedPreferencesRepository
): BaseViewModel<Any>() {

    override var _state: MutableStateFlow<Any>
        get() = TODO("Not yet implemented")
        set(value) {}
    override val state: StateFlow<Any>
        get() = TODO("Not yet implemented")

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