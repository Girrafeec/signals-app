/* Created by Girrafeec */

package com.girrafeecstud.on_board.data

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import javax.inject.Inject

class OnBoardSharedPreferencesDataSource @Inject constructor(
    private val applicationContext: Context
) {

    private var _sharedPreferences: SharedPreferences? = null

    private val sharedPreferences get () = _sharedPreferences!!

    companion object {
        private const val ON_BOARD_SHARED_PREFERENCES = "ON_BOARD_SHARED_PREFERENCES"
        private const val ON_BOARD_FINISHED = "ON_BOARD_FINISHED"
    }

    init {
        _sharedPreferences = applicationContext.getSharedPreferences(
            ON_BOARD_SHARED_PREFERENCES,
            AppCompatActivity.MODE_PRIVATE
        )
    }

    suspend fun getOnBoardStatus(): Boolean {
        return sharedPreferences.getBoolean(ON_BOARD_FINISHED, false)
    }

    suspend fun setOnBoardStatus(onBoardFinished: Boolean) {
        sharedPreferences
            .edit()
            .putBoolean(ON_BOARD_FINISHED, onBoardFinished)
            .apply()
    }

}