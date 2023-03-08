package com.girrafeecstud.society_safety_app.core_base.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<UiState>: ViewModel() {

    protected abstract var _state: MutableStateFlow<UiState>

    abstract val state: StateFlow<UiState>

    // TODO узнать про open
    protected open fun destroyComponent() {}

    protected open fun closeConnection() {}

}