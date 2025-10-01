package com.mj.appmvi.data.repository

import com.mj.appmvi.data.local.dao.TodoDao
import com.mj.appmvi.domain.mapper.toEntity
import com.mj.appmvi.domain.mapper.toModel
import com.mj.appmvi.domain.model.TodoItemModel
import com.mj.appmvi.domain.repository.RepositoryTodo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepositoryTodoImp @Inject constructor(val todoDao: TodoDao) : RepositoryTodo {
    override suspend fun insertTodo(todoItemModel: TodoItemModel) {
        todoDao.insertTodo(todoItemModel.toEntity())
    }

    override suspend fun updateTodo(todoItemModel: TodoItemModel) {
        todoDao.updateTodo(todoItemModel.toEntity())
    }

    override suspend fun deleteTodo(todoItemModel: TodoItemModel) {
        todoDao.deleteTodo(todoItemModel.toEntity())
    }

    override suspend fun getTodoList(): Flow<List<TodoItemModel>> = todoDao.getAllTodo().map {
        it.map { item -> item.toModel() }
    }

    override suspend fun getTodoById(id: Long): Flow<TodoItemModel> = todoDao.getTodoById(id).map { it.toModel() }
}