/* Created by Girrafeec */

package com.girrafeecstud.on_board.di

import com.girrafeecstud.on_board.di.annotation.OnBoardScope
import com.girrafeecstud.on_board.ui.OnBoardFragment
import dagger.Subcomponent

@OnBoardScope
@Subcomponent(
    modules = [OnBoardModule::class]
)
interface OnBoardComponent {

    fun inject(fragment: OnBoardFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): OnBoardComponent
    }

}