package com.mj.appmvi.presentation.details.intent

import com.mj.appmvi.domain.model.TodoItemModel

sealed interface UiTaskDetailsActions {
    data class FetchDetails(val id: Long?) : UiTaskDetailsActions
   data object OnBackPressed : UiTaskDetailsActions
    data class OnTaskUpdate(val todoItemModel: TodoItemModel) : UiTaskDetailsActions
    //
    object OnTaskSave : UiTaskDetailsActions
}