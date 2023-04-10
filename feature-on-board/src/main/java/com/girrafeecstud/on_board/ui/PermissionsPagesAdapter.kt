/* Created by Girrafeec */

package com.girrafeecstud.on_board.ui

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.girrafeecstud.on_board.R
import com.girrafeecstud.on_board.databinding.OnBoardPageItemBinding
import com.girrafeecstud.on_board.databinding.PermissionPageItemBinding
import com.girrafeecstud.on_board.databinding.PermissionTitlePageItemBinding
import javax.inject.Inject

class PermissionsPagesAdapter @Inject constructor(

) : RecyclerView.Adapter<PermissionsPageViewHolder>() {

    val pages =
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.TIRAMISU)
            listOf(
                PermissionPage.PermissionTitle(
                    title = "Предоставление разрешений",
                    description = "Для наилучшей работы приложения Signals, необходимо предоставить все необходимые разрешения",
                    icon = com.girrafeecstud.core_ui.R.drawable.permissions_not_accepted
                ),
                PermissionPage.PermissionPageItem(
                    title = "Для определения вашего местоположения",
                    description = "Приложению нужен доступ к вашей геолокации для определения вашего местоположения и быстрой доставки помощи",
                    icon = com.girrafeecstud.core_ui.R.drawable.permission_location,
                    permission = AppPermission.Location
                ),
                PermissionPage.PermissionPageItem(
                    title = "Для звонков в экстренных ситуациях",
                    description = "Приложению нужен доступ к вашим звонкам, чтобы вы могли быстро позвонить в экстренных ситуациях и вызвать помощь",
                    icon = com.girrafeecstud.core_ui.R.drawable.permission_call,
                    permission = AppPermission.Contacts
                ),
                PermissionPage.PermissionPageItem(
                    title = "Для быстрого контакта с близкими",
                    description = "Приложению нужен доступ к вашему списку контактов, чтобы вы могли быстро связаться с близкими людьми в случае необходимости",
                    icon = com.girrafeecstud.core_ui.R.drawable.permission_contacts,
                    permission = AppPermission.PhoneCall
                ),
                PermissionPage.PermissionPageItem(
                    title = "Для получения уведомлений о помощи",
                    description = "Приложению нужен доступ к вашим уведомлениям, чтобы вы могли получать важные уведомления о том, когда вышедшая на помощь команда отправится к вам",
                    icon = com.girrafeecstud.core_ui.R.drawable.permission_notifications,
                    permission = AppPermission.Notifications
                ),
                PermissionPage.PermissionTitle(
                    title = "Вы готовы использовать Signals на все 100%",
                    description = "Теперь вы можете использовать приложение Signals в полной мере",
                    icon = com.girrafeecstud.core_ui.R.drawable.permissions_accepted
                )
            )
    else
            listOf(
                PermissionPage.PermissionTitle(
                    title = "Предоставление разрешений",
                    description = "Для наилучшей работы приложения Signals, необходимо предоставить все необходимые разрешения",
                    icon = com.girrafeecstud.core_ui.R.drawable.permissions_not_accepted
                ),
                PermissionPage.PermissionPageItem(
                    title = "Для определения вашего местоположения",
                    description = "Приложению нужен доступ к вашей геолокации для определения вашего местоположения и быстрой доставки помощи",
                    icon = com.girrafeecstud.core_ui.R.drawable.permission_location,
                    permission = AppPermission.Location
                ),
                PermissionPage.PermissionPageItem(
                    title = "Для звонков в экстренных ситуациях",
                    description = "Приложению нужен доступ к вашим звонкам, чтобы вы могли быстро позвонить в экстренных ситуациях и вызвать помощь",
                    icon = com.girrafeecstud.core_ui.R.drawable.permission_call,
                    permission = AppPermission.Contacts
                ),
                PermissionPage.PermissionPageItem(
                    title = "Для быстрого контакта с близкими",
                    description = "Приложению нужен доступ к вашему списку контактов, чтобы вы могли быстро связаться с близкими людьми в случае необходимости",
                    icon = com.girrafeecstud.core_ui.R.drawable.permission_contacts,
                    permission = AppPermission.PhoneCall
                ),
                PermissionPage.PermissionTitle(
                    title = "Вы готовы использовать Signals на все 100%",
                    description = "Теперь вы можете использовать приложение Signals в полной мере",
                    icon = com.girrafeecstud.core_ui.R.drawable.permissions_accepted
                )
            )

    private sealed class PermissionPageViewType(
        val typeId: Int
    ) {
        object PermissionTitle : PermissionPageViewType(typeId = 0)
        object PermissionItem : PermissionPageViewType(typeId = 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PermissionsPageViewHolder {
        val binding = if (viewType == PermissionPageViewType.PermissionTitle.typeId) {
            PermissionTitlePageItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        }
        else {
            PermissionPageItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        }
        return PermissionsPageViewHolder(binding = binding)
    }

    override fun getItemCount(): Int {
        return pages.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (pages[position] is PermissionPage.PermissionTitle)
            PermissionPageViewType.PermissionTitle.typeId
        else
            PermissionPageViewType.PermissionItem.typeId
    }

    override fun onBindViewHolder(holder: PermissionsPageViewHolder, position: Int) {
        holder.bind(pages[position])
    }

}