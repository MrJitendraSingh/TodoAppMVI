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
import com.mj.appmvi.core.UiState
import com.mj.appmvi.domain.repository.RepositoryTodo
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
    //data layer domain layer
    private val repositoryTodo: RepositoryTodo
) : ViewModel() {


    private val _state: MutableStateFlow<UiState<UiTaskDetailsState>> =
        MutableStateFlow(UiState(state = UiTaskDetailsState(TodoItemModel())))
    val state = _state.asStateFlow()

    var initialTask by mutableStateOf(TodoItemModel())

    //clean it
    fun onAction(action: UiTaskDetailsActions){
        when(action){
            is UiTaskDetailsActions.FetchDetails -> {
                if (action.id != null) getTaskDetails(action.id)
            }
            UiTaskDetailsActions.OnBackPressed -> TODO()
            UiTaskDetailsActions.OnTaskSave -> {
                viewModelScope.launch(Dispatchers.IO){
                    state.value.state?.let {
                        repositoryTodo.insertTodo(it.taskDetails)
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

    //private
    fun getTaskDetails(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            //fix this logic
            repositoryTodo.getTodoById(id).collectLatest { details ->
                _state.update { it.copy(
                    state = it.state?.copy(details)
                ) }
            }
        }
    }

}