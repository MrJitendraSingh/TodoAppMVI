package com.mj.appmvi.presentation.tasks.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mj.appmvi.domain.repository.RepositoryTodo
import com.mj.appmvi.presentation.tasks.intent.UiTaskListActions
import com.mj.appmvi.presentation.tasks.intent.UiTaskListEffect
import com.mj.appmvi.presentation.tasks.intent.UiTaskListState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val repositoryTodo: RepositoryTodo
) : ViewModel(){

    private val _state : MutableStateFlow<UiTaskListState> = MutableStateFlow(UiTaskListState())
    val state = _state.asStateFlow()


    private val _effectChannel = Channel< UiTaskListEffect>()
    val effect = _effectChannel.receiveAsFlow()

    init {
        getTaskList()
    }

    fun onAction(action: UiTaskListActions) {
        when(action) {
            is UiTaskListActions.OnAddTaskClicked -> {
                viewModelScope.launch {
                    _effectChannel.send(UiTaskListEffect.NavigateToTaskCreate)
                }
            }

            is UiTaskListActions.OnTaskStatusChanged -> {
                viewModelScope.launch {
                    _effectChannel.send(UiTaskListEffect.NavigateToTaskDetail(action.taskId))
                }
            }
        }
    }


    fun getTaskList() {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryTodo.getTodoList().collectLatest { list ->
                _state.update { UiTaskListState(task = list as ArrayList) }
            }
        }
    }
}