package com.mj.appmvi.presentation.navigation

sealed class TodoRoute(val route: String) {
    object TaskListScreen : TodoRoute("TaskListScreen")
    object TaskDetailScreen : TodoRoute("TaskDetailScreen")
}