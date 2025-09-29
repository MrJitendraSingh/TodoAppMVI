package com.mj.appmvi.presentation.details.intent

import com.mj.appmvi.domain.model.TodoItemModel

data class UiTaskDetailsState(
    val isLoading: Boolean = false,
    val error: String = "",
    val taskDetails: TodoItemModel = TodoItemModel()
)