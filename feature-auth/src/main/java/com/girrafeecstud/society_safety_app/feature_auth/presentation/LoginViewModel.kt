package com.girrafeecstud.society_safety_app.feature_auth.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.girrafeecstud.society_safety_app.core_base.domain.base.BusinessResult
import com.girrafeecstud.society_safety_app.core_base.presentation.base.BaseViewModel
import com.girrafeecstud.society_safety_app.feature_auth.domain.entity.UserLoginEntity
import com.girrafeecstud.society_safety_app.feature_auth.domain.usecase.UserLoginUseCase
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginUseCase: UserLoginUseCase
) : BaseViewModel() {

    fun isUserPhoneNumberValid(userPhoneNmber: String) {

    }

    fun isUserPasswordValid(userPassword: String) {

    }

    fun login() {
        Log.i("tag", "clicked")
//        viewModelScope.launch {
//            loginUseCase(user = UserLoginEntity("", ""))
//                .onStart {
//                    setLoading()
//                }
//                .collect { result ->
//                    when (result) {
//                        is BusinessResult.Success -> {
//                            setSuccessResult(data = null)
//                        }
//                        is BusinessResult.Error -> {
//                            setError(data = null)
//                        }
//                        is BusinessResult.Exception -> {
//                            TODO("нужно ли отдельное состояние для exception?")
//                        }
//                    }
//                }
//        }
    }

    override fun closeConnection() {
        super.closeConnection()
    }
}