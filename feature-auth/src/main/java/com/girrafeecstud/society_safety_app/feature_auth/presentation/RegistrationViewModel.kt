package com.girrafeecstud.society_safety_app.feature_auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.girrafeecstud.society_safety_app.core_base.domain.base.BusinessResult
import com.girrafeecstud.society_safety_app.core_base.presentation.base.BaseViewModel
import com.girrafeecstud.society_safety_app.feature_auth.domain.entity.UserRegistrationEntity
import com.girrafeecstud.society_safety_app.feature_auth.domain.usecase.UserRegistratonUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val registrationUseCase: UserRegistratonUseCase
): BaseViewModel() {

    fun isUserPhoneNumberValid(userPhoneNumber: String) {

    }

    fun isUserPasswordValid(userPassword: String) {

    }

    fun registration(
        userPhoneNumber: String,
        userPassword: String,
        userFirstName: String,
        userLastName: String
    ) {
        viewModelScope.launch {
            registrationUseCase(
                user = UserRegistrationEntity(
                    userPhoneNumber = userPhoneNumber,
                    userPassword = userPassword,
                    userFirstName = userFirstName,
                    userLastName = userLastName
                )
            ).collect { result ->
                when (result) {
                    is BusinessResult.Success -> {
                        setSuccessResult(data = null)
                    }
                    is BusinessResult.Error -> {
                        setError(data = null)
                    }
                    is BusinessResult.Exception -> {
                        TODO("нужно ли отдельное состояние для exception?")
                    }
                }
            }
        }
    }

}