package com.mj.appmvi.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mj.appmvi.data.local.entity.TodoEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface TodoDao {

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todoEntity: TodoEntity)

    @Update
    suspend fun updateTodo(todoEntity: TodoEntity)

    @Delete
    suspend fun deleteTodo(todoEntity: TodoEntity)

    @Query("SELECT * FROM todo")
    fun getAllTodo(): Flow<List<TodoEntity>>

    @Query("SELECT * FROM todo WHERE id = :id")
    fun getTodoById(id: Long): Flow<TodoEntity>

}