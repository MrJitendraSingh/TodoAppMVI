package com.mj.appmvi.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class TodoRoute {

    @Serializable
    object TaskListScreen : TodoRoute()

    @Serializable
    data class TaskDetailScreen(val id: Long? = null) : TodoRoute()
}
