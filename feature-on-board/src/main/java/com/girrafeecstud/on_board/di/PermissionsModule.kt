package com.girrafeecstud.on_board.di

import com.girrafeecstud.on_board.di.annotation.PermissionsScope
import com.girrafeecstud.on_board.ui.PermissionsPagesAdapter
import dagger.Module
import dagger.Provides

@Module
class PermissionsModule {

    @Provides
    @PermissionsScope
    fun providePermissionsPagesAdapter() = PermissionsPagesAdapter()
}
