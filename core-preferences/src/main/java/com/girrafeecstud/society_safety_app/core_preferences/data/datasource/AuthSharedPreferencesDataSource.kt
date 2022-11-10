package com.girrafeecstud.society_safety_app.core_preferences.data.datasource

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import javax.inject.Inject

class AuthSharedPreferencesDataSource @Inject constructor(
    private val applicationContext: Context
) {

    private var _sharedPreferences: SharedPreferences? = null

    private val sharedPreferences get () = _sharedPreferences!!

    companion object {
        private const val SHARED_PREFERENCES = "SHARED_PREFERENCES"
        private const val USER_AUTHORIZED = "USER_AUTHORIZED"
        private const val USER_ID = "USER_ID" //TODO поменять на токен потом
    }

    init {
        _sharedPreferences = applicationContext.getSharedPreferences(
            SHARED_PREFERENCES,
            AppCompatActivity.MODE_PRIVATE
        )
    }

    suspend fun getUserAuthorizedStatus(): Boolean {
        return sharedPreferences.getBoolean(USER_AUTHORIZED, false)
    }

    suspend fun setUserAuthorized() {
        sharedPreferences
            .edit()
            .putBoolean(USER_AUTHORIZED, true)
            .apply()
    }

    suspend fun setUserUnauthorized() {
        sharedPreferences
            .edit()
            .putBoolean(USER_AUTHORIZED, false)
            .apply()
    }

    suspend fun getUserId(): String? {
        return sharedPreferences
            .getString(USER_ID, null)
    }

    suspend fun setUserId(userId: String) {
        sharedPreferences
            .edit()
            .putString(USER_ID, userId)
            .apply()
    }

    suspend fun clearUserId() {
        sharedPreferences
            .edit()
            .putString(USER_ID, null)
            .apply()
    }

}