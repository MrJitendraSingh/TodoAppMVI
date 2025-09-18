package com.mj.appmvi.presentation.tasks.intent

sealed interface UiTaskListActions {
    data class OnTaskClicked(val taskId: String): UiTaskListActions
    data object OnAddTaskClicked : UiTaskListActions
    data object OnBackPressed: UiTaskListActions
}