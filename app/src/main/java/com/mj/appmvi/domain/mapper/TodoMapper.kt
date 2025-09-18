package com.mj.appmvi.domain.mapper

import com.mj.appmvi.data.local.entity.TodoEntity
import com.mj.appmvi.domain.model.TodoItemModel

class TodoMapper {
}

fun TodoItemModel.toEntity() = TodoEntity(
    id = id?:0,
    title = title,
    description = description,
    isDone = isDone,
    createdAt = createdAt,
    dueDate = dueDate
)

fun TodoEntity.toModel() = TodoItemModel(
    id = id?:0,
    title = title,
    description = description,
    isDone = isDone,
    createdAt = createdAt,
    dueDate = dueDate
)