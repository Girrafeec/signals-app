/* Created by Girrafeec */

package com.girrafeecstud.push_notifications_impl.di

import com.girrafeecstud.core_components.di.CoreComponentsApi
import com.girrafeecstud.core_components.di.CoreComponentsComponent
import com.girrafeecstud.push_notifications_api.di.PushNotificationsFeatureApi
import com.girrafeecstud.push_notifications_impl.service.NotificationTokensService
import com.girrafeecstud.signals.auth_api.di.AuthFeatureApi
import com.girrafeecstud.signals.core_network.data.di.CoreNetworkApi
import com.girrafeecstud.signals.core_network.data.di.CoreNetworkComponent
import dagger.Component

@PushNotificationsScope
@Component(
    modules = [PushNotificationsFeatureModule::class],
    dependencies = [PushNotificationsFeatureDependencies::class]
)
interface PushNotificationsFeatureComponent : PushNotificationsFeatureApi {

    fun inject(service: NotificationTokensService)

    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: PushNotificationsFeatureDependencies): Builder

        fun build(): PushNotificationsFeatureComponent

    }

    companion object {

        private var _pushNotificationsFeatureComponent: PushNotificationsFeatureComponent? = null

        val pushNotificationsFeatureComponent get() = _pushNotificationsFeatureComponent!!

        fun init(dependencies: PushNotificationsFeatureDependencies) {
            if (_pushNotificationsFeatureComponent == null)
                _pushNotificationsFeatureComponent = DaggerPushNotificationsFeatureComponent
                    .builder()
                    .dependencies(dependencies = dependencies)
                    .build()
        }

        fun reset() {
            _pushNotificationsFeatureComponent = null
        }

    }

    @PushNotificationsScope
    @Component(
        dependencies = [
            CoreComponentsApi::class,
            CoreNetworkApi::class,
            AuthFeatureApi::class
        ]
    )
    interface PushNotificationsFeatureDependenciesComponent : PushNotificationsFeatureDependencies

}