package com.mj.appmvi.di

import android.app.Application
import androidx.room.Room
import com.mj.appmvi.core.Const
import com.mj.appmvi.data.local.TodoDatabase
import com.mj.appmvi.data.local.dao.TodoDao
import com.mj.appmvi.data.repository.RepositoryTodoImp
import com.mj.appmvi.domain.repository.RepositoryTodo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TodoAppModule {

    @Provides
    @Singleton
    fun provideTodoDatabase(application: Application): TodoDatabase {
        return Room.databaseBuilder(
            application,
            TodoDatabase::class.java,
            Const.DB_NAME).build()
    }

    @Provides
    fun provideTodoDao(todoDatabase: TodoDatabase) = todoDatabase.getTodoDao()


    //@Binds
    @Provides
    fun provideTodoRepoImpl(todoDao: TodoDao) : RepositoryTodo = RepositoryTodoImp(todoDao)

}