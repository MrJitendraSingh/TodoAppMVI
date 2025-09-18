package com.mj.appmvi.presentation.tasks.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mj.appmvi.data.repository.RepositoryTodoImp
import com.mj.appmvi.domain.model.TodoItemModel
import com.mj.appmvi.presentation.details.model.TaskDetailsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val repositoryTodoImp: RepositoryTodoImp
) : ViewModel(){

    private val _taskList = MutableStateFlow<List<TodoItemModel>>(emptyList())
    val taskList = _taskList.asStateFlow()


    fun getTaskList() {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryTodoImp.getTodoList().collect {
                _taskList.value = it
            }
        }
    }
}