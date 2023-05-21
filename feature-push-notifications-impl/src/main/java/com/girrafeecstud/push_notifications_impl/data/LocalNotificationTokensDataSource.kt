package com.girrafeecstud.push_notifications_impl.data

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.girrafeecstud.push_notifications_api.data.INotificationTokensDataSource
import com.girrafeecstud.signals.core_base.domain.base.BusinessErrorType
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalNotificationTokensDataSource @Inject constructor(
    private val context: Context
) : INotificationTokensDataSource {

    private var _sharedPreferences: SharedPreferences? = null

    private val sharedPreferences get () = _sharedPreferences!!

    companion object {
        private const val SHARED_PREFERENCES = "SHARED_PREFERENCES"
        private const val NOTIFICATION_TOKEN = "NOTIFICATION_TOKEN"
        private const val NOTIFICATION_TOKEN_SENT = "NOTIFICATION_TOKEN_SENT"
    }

    init {
        _sharedPreferences = context.getSharedPreferences(
            SHARED_PREFERENCES,
            AppCompatActivity.MODE_PRIVATE
        )
    }

    override suspend fun setNotificationTokenSent() =
        sharedPreferences
            .edit()
            .putBoolean(NOTIFICATION_TOKEN_SENT, true)
            .apply()

    override suspend fun setNotificationTokenNotSent() =
        sharedPreferences
            .edit()
            .putBoolean(NOTIFICATION_TOKEN_SENT, false)
            .apply()

    override suspend fun isNotificationTokenSent(): Boolean =
        sharedPreferences.getBoolean(NOTIFICATION_TOKEN_SENT, false)

    override suspend fun getNotificationToken(): Flow<BusinessResult<String>> =
        flow {
            val result = sharedPreferences
                .getString(NOTIFICATION_TOKEN, null)

            if (result.equals(null))
                emit(BusinessResult.Error(businessErrorType = BusinessErrorType.NOTIFICATION_TOKEN_NOT_EXISTS))
            emit(BusinessResult.Success(_data = result))
        }

    override suspend fun setNotificationToken(notificationToken: String) =
        sharedPreferences
            .edit()
            .putString(NOTIFICATION_TOKEN, notificationToken)
            .apply()
}
