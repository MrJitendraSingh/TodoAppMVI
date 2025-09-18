package com.mj.appmvi.domain.repository

import com.mj.appmvi.domain.model.TodoItemModel
import kotlinx.coroutines.flow.Flow

interface RepositoryTodo {
    suspend fun insertTodo(todoItemModel: TodoItemModel)
    suspend fun updateTodo(todoItemModel: TodoItemModel)
    suspend fun deleteTodo(todoItemModel: TodoItemModel)
    suspend fun getTodoList(): Flow<List<TodoItemModel>>
    suspend fun getTodoById(id: Int): Flow<TodoItemModel>
}