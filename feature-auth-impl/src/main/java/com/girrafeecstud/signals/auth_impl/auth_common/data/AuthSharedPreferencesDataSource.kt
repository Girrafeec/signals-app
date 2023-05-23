/* Created by Girrafeec */

package com.girrafeecstud.signals.auth_impl.auth_common.data

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.girrafeecstud.signals.auth_api.data.IAuthDataSource
import com.girrafeecstud.signals.core_base.domain.base.BusinessErrorType
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthSharedPreferencesDataSource @Inject constructor(
    private val applicationContext: Context
) : IAuthDataSource {

    private var _sharedPreferences: SharedPreferences? = null

    private val sharedPreferences get () = _sharedPreferences!!

    companion object {
        private const val SHARED_PREFERENCES = "SHARED_PREFERENCES"
        private const val USER_AUTHORIZED = "USER_AUTHORIZED"
        private const val USER_TOKEN = "USER_TOKEN"
    }

    init {
        _sharedPreferences = applicationContext.getSharedPreferences(
            SHARED_PREFERENCES,
            AppCompatActivity.MODE_PRIVATE
        )
    }

    override fun getUserAuthorizedStatus(): Boolean =
        sharedPreferences.getBoolean(USER_AUTHORIZED, false)

    override fun setUserAuthorized() =
        sharedPreferences
            .edit()
            .putBoolean(USER_AUTHORIZED, true)
            .apply()

    override fun setUserUnauthorized() =
        sharedPreferences
            .edit()
            .putBoolean(USER_AUTHORIZED, false)
            .apply()

    override fun getUserToken(): Flow<BusinessResult<String>> =
        flow {
            val result = sharedPreferences
                .getString(USER_TOKEN, null)

            if (result.equals(null))
                emit(BusinessResult.Error(businessErrorType = BusinessErrorType.USER_UNAUTHORIZED))
            emit(BusinessResult.Success(_data = result))
        }

    override fun setUserToken(userToken: String) =
        sharedPreferences
            .edit()
            .putString(USER_TOKEN, userToken)
            .apply()

    override fun clearUserToken() =
        sharedPreferences
            .edit()
            .putString(USER_TOKEN, null)
            .apply()
}