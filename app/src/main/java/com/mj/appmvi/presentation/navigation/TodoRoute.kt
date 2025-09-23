package com.mj.appmvi.presentation.navigation

sealed class TodoRoute(val route: String) {
    object TaskListScreen : TodoRoute("TaskListScreen")
    data class TaskDetailScreen(val id: Long? = null) : TodoRoute("TaskDetailScreen")
}