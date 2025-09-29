package com.mj.appmvi.presentation.tasks.intent

sealed class UiTaskListEffect {
    data class NavigateToTaskDetail(val taskId: Long) : UiTaskListEffect()
    object NavigateToTaskCreate : UiTaskListEffect()
}