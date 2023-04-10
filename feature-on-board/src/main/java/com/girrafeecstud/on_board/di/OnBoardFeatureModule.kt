package com.girrafeecstud.on_board.di

import android.content.Context
import com.girrafeecstud.on_board.data.OnBoardSharedPreferencesDataSource
import com.girrafeecstud.on_board.di.annotation.OnBoardFeatureScope
import dagger.Module
import dagger.Provides

@Module(
    subcomponents = [OnBoardComponent::class, PermissionsComponent::class]
)
class OnBoardFeatureModule {

    @Provides
    @OnBoardFeatureScope
    fun provideOnBoardSharedPreferencesDataSource(context: Context) =
        OnBoardSharedPreferencesDataSource(applicationContext = context)

}
