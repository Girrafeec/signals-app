package com.girrafeecstud.signals.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.girrafeecstud.core_ui.presentation.BaseViewModel
import com.girrafeecstud.signals.core_preferences.data.repository.AuthSharedPreferencesRepository
import com.girrafeecstud.on_board.data.OnBoardSharedPreferencesDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: AuthSharedPreferencesRepository,
    private val onBoardDataSource: OnBoardSharedPreferencesDataSource
): BaseViewModel<Any>() {

    override var _state: MutableStateFlow<Any>
        get() = TODO("Not yet implemented")
        set(value) {}
    override val state: StateFlow<Any>
        get() = TODO("Not yet implemented")
    private val userAuthorizedStatus =  MutableLiveData<Boolean>()

    private val _isUserOnBoarded: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isUserOnBoarded = _isUserOnBoarded.asStateFlow()

    init {
        Log.i("tag start", "vm init")
        viewModelScope.launch {
            _isUserOnBoarded.update { onBoardDataSource.getOnBoardStatus() }
            Log.i("tag start", "onboarded: ${_isUserOnBoarded.value}")
        }
    }

    fun requestUserAuthorizedStatus() {
        viewModelScope.launch {
            userAuthorizedStatus.value = repository.getUserAuthorizedStatus()
        }
    }

    fun getUserAuthorizedStatus(): LiveData<Boolean> = userAuthorizedStatus

    override fun onCleared() {
        Log.i("tag start", "vm clear")
    }
}