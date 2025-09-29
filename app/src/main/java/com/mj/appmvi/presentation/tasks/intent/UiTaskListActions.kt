package com.mj.appmvi.presentation.tasks.intent

sealed interface UiTaskListActions {
    data object OnAddTaskClicked : UiTaskListActions
    data class OnTaskStatusChanged(val taskId: Long): UiTaskListActions
}