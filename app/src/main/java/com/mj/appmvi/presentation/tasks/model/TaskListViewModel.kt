package com.mj.appmvi.presentation.tasks.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mj.appmvi.data.repository.RepositoryTodoImp
import com.mj.appmvi.presentation.tasks.intent.UiTaskListState
import com.mj.appmvi.core.UiState
import com.mj.appmvi.domain.repository.RepositoryTodo
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
    private val repositoryTodo: RepositoryTodo
) : ViewModel(){

    private val _state : MutableStateFlow<UiState<UiTaskListState>> = MutableStateFlow(UiState(state = UiTaskListState()))
    val state = _state.asStateFlow()


    init {
        getTaskList()
    }
    fun getTaskList() {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryTodo.getTodoList().collectLatest { list ->
                _state.update { it.copy(state = UiTaskListState(task = list as ArrayList)) }
            }
        }
    }
}