package com.mj.appmvi.presentation.tasks.state

sealed interface UiTaskListActions {
    data class
    data object OnBackPressed: UiTaskListActions
}