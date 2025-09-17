package com.mj.appmvi.domain.model

data class TodoItemModel (
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val isDone: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val dueDate: Long? = null
)