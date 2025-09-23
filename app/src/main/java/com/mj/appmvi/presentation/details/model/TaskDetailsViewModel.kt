package com.mj.appmvi.presentation.details.model

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mj.appmvi.data.repository.RepositoryTodoImp
import com.mj.appmvi.domain.mapper.toEntity
import com.mj.appmvi.domain.model.TodoItemModel
import com.mj.appmvi.presentation.details.intent.UiTaskDetailsActions
import com.mj.appmvi.presentation.details.intent.UiTaskDetailsState
import com.mj.appmvi.presentation.tasks.intent.UiTaskListState
import com.mj.appmvi.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TaskDetailsViewModel @Inject constructor(
    private val repositoryTodoImp: RepositoryTodoImp
) : ViewModel() {


    private val _state: MutableStateFlow<UiState<UiTaskDetailsState>> =
        MutableStateFlow(UiState(state = UiTaskDetailsState(TodoItemModel())))
    val state = _state.asStateFlow()

    var initialTask by mutableStateOf(TodoItemModel())

    fun onAction(action: UiTaskDetailsActions){
        when(action){
            is UiTaskDetailsActions.FetchDetails -> {
                getTaskDetails(action.id)
            }
            UiTaskDetailsActions.OnBackPressed -> TODO()
            UiTaskDetailsActions.OnTaskSave -> {
                viewModelScope.launch(Dispatchers.IO){
                    state.value.state?.let {
                        repositoryTodoImp.todoDao.insertTodo(it.taskDetails.toEntity())
                    }
                }
            }
            is UiTaskDetailsActions.OnTaskUpdate -> {
                _state.update {
                    Log.e("TAG", "onAction: ", )
                    it.copy(
                        state = it.state?.copy(
                            taskDetails = action.todoItemModel
                        )
                    )
                }
            }

        }
    }

    fun getTaskDetails(id: Long?) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryTodoImp.getTodoById(id!!).collectLatest { details ->
                _state.update { it.copy(
                    state = it.state?.copy(details)
                ) }
            }
        }
    }

}