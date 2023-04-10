/* Created by Girrafeec */

package com.girrafeecstud.on_board.di

import com.girrafeecstud.core_components.di.CoreComponentsApi
import com.girrafeecstud.on_board.di.annotation.OnBoardFeatureScope
import com.girrafeecstud.on_board.ui.OnBoardFlowFragment
import dagger.Component

@OnBoardFeatureScope
@Component(
    modules = [OnBoardFeatureModule::class],
    dependencies = [OnBoardFeatureDependencies::class]
)
interface OnBoardFeatureComponent : OnBoardFeatureApi {

    fun inject(fragment: OnBoardFlowFragment)

    fun onBoardComponent(): OnBoardComponent.Builder

    fun permissionsComponent(): PermissionsComponent.Builder

    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: OnBoardFeatureDependencies): Builder

        fun build(): OnBoardFeatureComponent

    }

    companion object {

        private var _onBoardFeatureComponent: OnBoardFeatureComponent? = null

        val onBoardFeatureComponent get() = _onBoardFeatureComponent!!

        fun init(dependencies: OnBoardFeatureDependencies) {
            if (_onBoardFeatureComponent == null)
                _onBoardFeatureComponent = DaggerOnBoardFeatureComponent
                    .builder()
                    .dependencies(dependencies = dependencies)
                    .build()
        }

        fun reset() {
            _onBoardFeatureComponent = null
        }

    }

    @OnBoardFeatureScope
    @Component(
        dependencies = [CoreComponentsApi::class]
    )
    interface OnBoardFeatureDependenciesComponent : OnBoardFeatureDependencies

}