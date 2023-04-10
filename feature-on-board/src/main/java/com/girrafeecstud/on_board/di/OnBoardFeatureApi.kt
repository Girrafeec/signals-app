/* Created by Girrafeec */

package com.girrafeecstud.on_board.di

import com.girrafeecstud.on_board.data.OnBoardSharedPreferencesDataSource

interface OnBoardFeatureApi {

    fun getOnBoardSharedPreferencesDataSource(): OnBoardSharedPreferencesDataSource

}