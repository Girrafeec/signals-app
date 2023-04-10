package com.girrafeecstud.on_board.di

import com.girrafeecstud.on_board.di.annotation.PermissionsScope
import com.girrafeecstud.on_board.ui.PermissionsFragment
import dagger.Subcomponent

@PermissionsScope
@Subcomponent(
    modules = [PermissionsModule::class]
)
interface PermissionsComponent {

    fun inject(fragment: PermissionsFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): PermissionsComponent
    }

}
