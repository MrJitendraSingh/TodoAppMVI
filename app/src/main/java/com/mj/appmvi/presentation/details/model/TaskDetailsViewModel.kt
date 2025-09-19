package com.mj.appmvi.presentation.details.model

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.mj.appmvi.data.repository.RepositoryTodoImp
import com.mj.appmvi.domain.model.TodoItemModel
import com.mj.appmvi.presentation.details.intent.UiTaskDetailsActions
import com.mj.appmvi.presentation.details.intent.UiTaskDetailsState
import com.mj.appmvi.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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
            UiTaskDetailsActions.FetchDetails -> TODO()
            UiTaskDetailsActions.OnBackPressed -> TODO()
            is UiTaskDetailsActions.OnTaskAdded -> {
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

}