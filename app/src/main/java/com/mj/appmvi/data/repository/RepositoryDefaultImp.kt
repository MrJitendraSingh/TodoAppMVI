package com.mj.appmvi.data.repository

import com.mj.appmvi.data.local.dao.TodoDao
import com.mj.appmvi.data.local.entity.TodoEntity
import com.mj.appmvi.domain.model.TodoItemModel
import com.mj.appmvi.domain.repository.RepositoryTodo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class RepositoryDefaultImp @Inject constructor(todoDao: TodoDao) : RepositoryTodo {
    override suspend fun insertTodo(todoItemModel: TodoItemModel) {

    }

    override suspend fun updateTodo(todoItemModel: TodoItemModel) {

    }

    override suspend fun deleteTodo(todoItemModel: TodoItemModel) {

    }

    override suspend fun getTodoList(): Flow<List<TodoItemModel>> {

        val dataList = listOf(
            TodoItemModel(1, "Todo 1", "Description 1", false),
            TodoItemModel(2, "Todo 2", "Description 2", true),
            TodoItemModel(3, "Todo 3", "Description 3", false)
        )

        return flowOf(dataList)
    }

    override suspend fun getTodoById(id: Long): Flow<TodoItemModel> {
        return flowOf(TodoItemModel())
    }
}