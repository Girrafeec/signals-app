/* Created by Girrafeec */

package com.girrafeecstud.on_board.ui

import android.content.pm.PathPermission
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.girrafeecstud.on_board.databinding.PermissionPageItemBinding
import com.girrafeecstud.on_board.databinding.PermissionTitlePageItemBinding

class PermissionsPageViewHolder(
    private val binding: ViewBinding
) : RecyclerView.ViewHolder(binding.root) {

    private fun bindItem(page: PermissionPage.PermissionPageItem) {

        binding as PermissionPageItemBinding

        binding.slideImage.setImageResource(page.icon)
        binding.textDescription.text = page.description
        binding.textTitle.text = page.title
    }

    private fun bindTitle(page: PermissionPage.PermissionTitle) {
        binding as PermissionTitlePageItemBinding

        binding.slideImage.setImageResource(page.icon)
        binding.textDescription.text = page.description
        binding.textTitle.text = page.title
    }

    fun bind(page: PermissionPage) {
        when (page) {
            is PermissionPage.PermissionPageItem -> bindItem(page = page)
            is PermissionPage.PermissionTitle -> bindTitle(page = page)
        }
    }

}