package com.girrafeecstud.signals.feature_auth.presentation

import com.girrafeecstud.signals.core_base.presentation.base.BaseViewModel
import com.girrafeecstud.signals.feature_auth.domain.usecase.UserRegistratonUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val registrationUseCase: UserRegistratonUseCase
): BaseViewModel<Any>() {

    override var _state: MutableStateFlow<Any>
        get() = TODO("Not yet implemented")
        set(value) {}
    override val state: StateFlow<Any>
        get() = TODO("Not yet implemented")

    fun isUserPhoneNumberValid(userPhoneNumber: String) {

    }

    fun isUserPasswordValid(userPassword: String) {

    }

//    fun registration(
//        userPhoneNumber: String,
//        userPassword: String,
//        userFirstName: String,
//        userLastName: String
//    ) {
//        viewModelScope.launch {
//            registrationUseCase(
//                user = UserRegistrationEntity(
//                    userPhoneNumber = userPhoneNumber,
//                    userPassword = userPassword,
//                    userFirstName = userFirstName,
//                    userLastName = userLastName
//                )
//            ).collect { result ->
//                when (result) {
//                    is BusinessResult.Success -> {
//                        Log.i("taqg", "succ vm")
//                        setSuccess(data = null)
//                    }
//                    is BusinessResult.Error -> {
//                        println(result.businessErrorType)
//                        setError(data = null)
//                    }
//                    is BusinessResult.Exception -> {
//                        println(result.exceptionType)
//                        //TODO нужно ли отдельное состояние для exception?
//                        setError(data = null)
//                    }
//                }
//            }
//        }
//    }

}