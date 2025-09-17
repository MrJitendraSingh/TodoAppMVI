package com.mj.appmvi.domain.repository

import com.mj.appmvi.domain.model.TodoItemModel
import kotlinx.coroutines.flow.Flow

interface RepositoryTodo {
    suspend fun getTodos(): Flow<List<TodoItemModel>>
    suspend fun getTodoDetails(): Flow<TodoItemModel>
}