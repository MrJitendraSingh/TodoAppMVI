package com.mj.appmvi.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mj.appmvi.core.Const
import com.mj.appmvi.data.local.dao.TodoDao
import com.mj.appmvi.data.local.entity.TodoEntity

@Database(entities = [TodoEntity::class], version = 1)
abstract class TodoDatabase : RoomDatabase(){

    abstract fun getTodoDao(): TodoDao

    companion object {
        fun getInstance(context: Context) = Room.databaseBuilder(
            context,
            TodoDatabase::class.java,
            Const.DB_NAME).build()
    }
}