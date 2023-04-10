package com.girrafeecstud.on_board.di

import com.girrafeecstud.on_board.di.annotation.OnBoardScope
import com.girrafeecstud.on_board.ui.OnBoardSlidesAdapter
import dagger.Module
import dagger.Provides

@Module
class OnBoardModule {

    @Provides
    @OnBoardScope
    fun provideOnBoardSlidesAdapter() = OnBoardSlidesAdapter()

}
