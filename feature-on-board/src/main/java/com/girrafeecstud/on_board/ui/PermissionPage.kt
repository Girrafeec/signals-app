package com.girrafeecstud.on_board.ui

sealed class PermissionPage {

    data class PermissionPageItem(
        val icon: Int,
        val title: String,
        val description: String,
        val permission: AppPermission
    ) : PermissionPage()

    data class PermissionTitle(
        val icon: Int,
        val title: String,
        val description: String
    ) : PermissionPage()
}
