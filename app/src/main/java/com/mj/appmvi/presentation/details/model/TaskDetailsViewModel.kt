package com.mj.appmvi.presentation.details.model

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.mj.appmvi.data.repository.RepositoryTodoImp
import com.mj.appmvi.domain.model.TodoItemModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class TaskDetailsViewModel @Inject constructor(
    private val repositoryTodoImp: RepositoryTodoImp
) : ViewModel() {

    var initialTask by mutableStateOf(TodoItemModel())

}