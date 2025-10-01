package com.mj.appmvi.presentation.details.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mj.appmvi.domain.repository.RepositoryTodo
import com.mj.appmvi.presentation.details.intent.UiTaskDetailsActions
import com.mj.appmvi.presentation.details.intent.UiTaskDetailsEffect
import com.mj.appmvi.presentation.details.intent.UiTaskDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TaskDetailsViewModel @Inject constructor(
    private val repositoryTodo: RepositoryTodo
) : ViewModel() {


    private val _state: MutableStateFlow<UiTaskDetailsState> = MutableStateFlow(UiTaskDetailsState())
    val state = _state.asStateFlow()

    private val _effectChannel = Channel<UiTaskDetailsEffect>()
    val effect = _effectChannel.receiveAsFlow()




    //clean it
    fun onAction(action: UiTaskDetailsActions) {
        when(action){
            is UiTaskDetailsActions.FetchDetails -> {
                if (action.id != null) getTaskDetails(action.id)
            }
            UiTaskDetailsActions.OnBackPressed -> {
                viewModelScope.launch {
                    _effectChannel.send(UiTaskDetailsEffect.NavigateBack)
                }
            }
            UiTaskDetailsActions.OnTaskSave -> {
                viewModelScope.launch(Dispatchers.IO){
                    _state.value.let {
                        repositoryTodo.insertTodo(it.taskDetails)
                    }
                    _effectChannel.send(UiTaskDetailsEffect.NavigateBack)
                }
            }
            is UiTaskDetailsActions.OnTaskUpdate -> {
                _state.update {
                    it.copy(taskDetails = action.todoItemModel)
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
                    taskDetails = details
                ) }
            }
        }
    }

}