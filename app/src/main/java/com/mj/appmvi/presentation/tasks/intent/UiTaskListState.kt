package com.mj.appmvi.presentation.tasks.intent

import com.mj.appmvi.domain.model.TodoItemModel

data class UiTaskListState (
    val task : List<TodoItemModel> = emptyList()
)