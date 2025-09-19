package com.mj.appmvi.presentation.details.intent

import com.mj.appmvi.domain.model.TodoItemModel

data class UiTaskDetailsState(
    val taskDetails: TodoItemModel = TodoItemModel()
)