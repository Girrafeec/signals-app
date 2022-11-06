package com.girrafeecstud.society_safety_app.core_base.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel() {

    private val state = MutableLiveData<MainState>()

    protected fun setLoading() {
        state.value = MainState.IsLoading(isLoading = true)
    }

    protected fun hideLoading() {
        state.value = MainState.IsLoading(isLoading = false)
    }

    protected fun setError(data: Any?) {
        state.value = MainState.ErrorResult(data = data)
    }


    protected fun setSuccessResult(data: Any?) {
        state.value = MainState.SuccessResult(data = data)
    }

    fun getState(): LiveData<MainState> {
        return state
    }

    // TODO узнать про open
    protected open fun destroyComponent() {}

    protected open fun closeConnection() {}

}