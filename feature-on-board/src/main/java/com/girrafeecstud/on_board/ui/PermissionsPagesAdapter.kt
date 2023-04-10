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
                    title = "title 1",
                    description = "description 1",
                    icon = com.girrafeecstud.core_ui.R.drawable.ic_launcher_background
                ),
                PermissionPage.PermissionPageItem(
                    title = "Местоположение",
                    description = "description 1",
                    icon = com.girrafeecstud.core_ui.R.drawable.ic_launcher_background,
                    permission = AppPermission.Location
                ),
                PermissionPage.PermissionPageItem(
                    title = "Список контактов",
                    description = "description 2",
                    icon = com.girrafeecstud.core_ui.R.drawable.ic_launcher_background,
                    permission = AppPermission.Contacts
                ),
                PermissionPage.PermissionPageItem(
                    title = "Звонки",
                    description = "description 3",
                    icon = com.girrafeecstud.core_ui.R.drawable.ic_launcher_background,
                    permission = AppPermission.PhoneCall
                ),
                PermissionPage.PermissionPageItem(
                    title = "Уведомления",
                    description = "description 4",
                    icon = com.girrafeecstud.core_ui.R.drawable.ic_launcher_background,
                    permission = AppPermission.Notifications
                ),
                PermissionPage.PermissionTitle(
                    title = "title 6",
                    description = "description 6",
                    icon = com.girrafeecstud.core_ui.R.drawable.ic_launcher_background
                )
            )
    else
            listOf(
                PermissionPage.PermissionTitle(
                    title = "title 1",
                    description = "description 1",
                    icon = com.girrafeecstud.core_ui.R.drawable.ic_launcher_background
                ),
                PermissionPage.PermissionPageItem(
                    title = "Местоположение",
                    description = "description 1",
                    icon = com.girrafeecstud.core_ui.R.drawable.ic_launcher_background,
                    permission = AppPermission.Location
                ),
                PermissionPage.PermissionPageItem(
                    title = "Список контактов",
                    description = "description 2",
                    icon = com.girrafeecstud.core_ui.R.drawable.ic_launcher_background,
                    permission = AppPermission.Contacts
                ),
                PermissionPage.PermissionPageItem(
                    title = "Звонки",
                    description = "description 3",
                    icon = com.girrafeecstud.core_ui.R.drawable.ic_launcher_background,
                    permission = AppPermission.PhoneCall
                ),
                PermissionPage.PermissionTitle(
                    title = "title 6",
                    description = "description 6",
                    icon = com.girrafeecstud.core_ui.R.drawable.ic_launcher_background
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