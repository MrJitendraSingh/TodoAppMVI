package com.mj.appmvi.data.di

import android.app.Application
import com.mj.appmvi.data.local.TodoDatabase
import com.mj.appmvi.data.local.dao.TodoDao
import com.mj.appmvi.data.repository.RepositoryTodoImp
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
        return TodoDatabase.getInstance(application)
    }

    @Provides
    fun provideTodoDao(todoDatabase: TodoDatabase) = todoDatabase.getTodoDao()

    @Provides
    fun provideTodoRepoImpl(todoDao: TodoDao) : RepositoryTodoImp = RepositoryTodoImp(todoDao)

}