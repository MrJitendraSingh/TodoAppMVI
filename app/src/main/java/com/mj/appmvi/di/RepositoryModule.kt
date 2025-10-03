package com.mj.appmvi.di

import com.mj.appmvi.core.TodoDefaultRepo
import com.mj.appmvi.core.TodoRemoteRepo
import com.mj.appmvi.data.repository.RepositoryDefaultImp
import com.mj.appmvi.data.repository.RepositoryTodoImp
import com.mj.appmvi.domain.repository.RepositoryTodo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {


    @Binds
    @TodoRemoteRepo
    abstract fun bindRepository(repositoryImpl: RepositoryTodoImp): RepositoryTodo


    @Binds
    @TodoDefaultRepo
    abstract fun bindDefaultRepository(repositoryImpl: RepositoryDefaultImp): RepositoryTodo
}
