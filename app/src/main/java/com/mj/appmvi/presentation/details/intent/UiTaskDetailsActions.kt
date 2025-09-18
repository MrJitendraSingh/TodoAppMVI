package com.mj.appmvi.presentation.details.intent

import com.mj.appmvi.domain.model.TodoItemModel

sealed class UiTaskDetailsActions {
    object FetchDetails : UiTaskDetailsActions()
    object OnBackPressed : UiTaskDetailsActions()
    data class OnTaskAdded(val todoItemModel: TodoItemModel) : UiTaskDetailsActions()
}