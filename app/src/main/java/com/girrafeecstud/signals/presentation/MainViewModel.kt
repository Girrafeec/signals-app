package com.girrafeecstud.signals.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.girrafeecstud.signals.core_base.presentation.base.BaseViewModel
import com.girrafeecstud.signals.core_preferences.data.repository.AuthSharedPreferencesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: AuthSharedPreferencesRepository
): BaseViewModel<Any>() {

    override var _state: MutableStateFlow<Any>
        get() = TODO("Not yet implemented")
        set(value) {}
    override val state: StateFlow<Any>
        get() = TODO("Not yet implemented")
    private val userAuthorizedStatus =  MutableLiveData<Boolean>()

    fun requestUserAuthorizedStatus() {
        viewModelScope.launch {
            userAuthorizedStatus.value = repository.getUserAuthorizedStatus()
        }
    }

    fun getUserAuthorizedStatus(): LiveData<Boolean> = userAuthorizedStatus

}