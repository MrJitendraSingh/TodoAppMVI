package com.mj.appmvi.presentation.tasks.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mj.appmvi.data.repository.RepositoryTodoImp
import com.mj.appmvi.domain.model.TodoItemModel
import com.mj.appmvi.presentation.details.model.TaskDetailsViewModel
import com.mj.appmvi.presentation.tasks.intent.UiTaskListState
import com.mj.appmvi.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val repositoryTodoImp: RepositoryTodoImp
) : ViewModel(){

    private val _taskList = MutableStateFlow<List<TodoItemModel>>(emptyList())
    val taskList = _taskList.asStateFlow()

    private val _state : MutableStateFlow<UiState<UiTaskListState>> = MutableStateFlow(UiState(state = UiTaskListState()))
    val state = _state.asStateFlow()


    fun getTaskList() {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryTodoImp.getTodoList().collectLatest { list ->
                _state.update { it.copy(state = UiTaskListState(task = list as ArrayList)) }
            }
        }
    }
}