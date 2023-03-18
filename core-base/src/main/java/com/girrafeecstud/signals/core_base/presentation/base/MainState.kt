package com.girrafeecstud.signals.core_base.presentation.base

sealed class MainState {
    data class IsLoading(val isLoading: Boolean): MainState()
    data class SuccessResult(val data: Any?): MainState()
    data class ErrorResult(val data: Any?): MainState()
}