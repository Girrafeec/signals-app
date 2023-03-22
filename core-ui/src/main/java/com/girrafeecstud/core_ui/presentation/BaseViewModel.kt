package com.girrafeecstud.core_ui.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<UiState>: ViewModel() {

    protected abstract var _state: MutableStateFlow<UiState>

    abstract val state: StateFlow<UiState>

    // TODO узнать про open
    protected open fun destroyComponent() {}

    protected open fun closeConnection() {}

}