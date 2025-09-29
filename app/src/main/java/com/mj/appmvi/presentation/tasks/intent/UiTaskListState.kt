package com.mj.appmvi.presentation.tasks.intent

import com.mj.appmvi.domain.model.TodoItemModel

data class UiTaskListState (
    val isLoading : Boolean = false,
    val error : String = "",
    val task : List<TodoItemModel> = emptyList()
)