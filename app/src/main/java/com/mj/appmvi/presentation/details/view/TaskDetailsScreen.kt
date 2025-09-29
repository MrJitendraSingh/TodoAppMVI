package com.mj.appmvi.presentation.details.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mj.appmvi.R
import com.mj.appmvi.core.BackTitleBar
import com.mj.appmvi.core.DateSelector
import com.mj.appmvi.core.PrimaryButton
import com.mj.appmvi.core.TextInputField
import com.mj.appmvi.domain.model.TodoItemModel
import com.mj.appmvi.presentation.details.intent.UiTaskDetailsActions
import com.mj.appmvi.presentation.details.intent.UiTaskDetailsEffect
import com.mj.appmvi.presentation.details.intent.UiTaskDetailsState
import com.mj.appmvi.presentation.details.model.TaskDetailsViewModel
import com.mj.appmvi.presentation.ui.theme.PreviewWrapper


@Composable
fun TaskDetailsPreview() {
    TaskDetailsScreen(null) {

    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailsScreen(id: Long?, onNavigation:() -> Unit) {
    // State variables (if editing, pre-fill them)
    val viewModel: TaskDetailsViewModel = hiltViewModel()
    val uiState by viewModel.state.collectAsStateWithLifecycle()


    LaunchedEffect(Unit) {
        //intent
        viewModel.onAction(UiTaskDetailsActions.FetchDetails(id))

        viewModel.effect.collect { event ->
            when (event) {
                UiTaskDetailsEffect.NavigateBack -> onNavigation()
                UiTaskDetailsEffect.ShowError -> {}
                UiTaskDetailsEffect.SaveSuccess -> {}
            }
        }
    }
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    )
    {
        BackTitleBar(stringResource(R.string.task_details)) {
            viewModel.onAction(UiTaskDetailsActions.OnBackPressed)
        }
        UiDetails(uiState.taskDetails) {
            viewModel.onAction(it)
        }

    }
}


@Preview
@Composable
fun UiDetailsPreview() {
    PreviewWrapper {
        UiDetails(TodoItemModel(1, "Title", "Description",false)) {

        }
    }
}


@Composable
fun UiDetails(
    details: TodoItemModel,
    onIntent: (UiTaskDetailsActions) -> Unit
){
    Column(Modifier.fillMaxWidth()
        .padding(horizontal = 16.dp)) {
        TextInputField(
            value = details.title,
            onValueChange = {
                onIntent(UiTaskDetailsActions.OnTaskUpdate(details.copy(title = it))) }
        )

        TextInputField(
            value = details.description,
            onValueChange = {
                onIntent(UiTaskDetailsActions.OnTaskUpdate(details.copy(description = it)))
            },
            label = stringResource(R.string.description)
        )

        //Seperate compoent

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Completed")
            Switch(
                checked = details.isDone,
                onCheckedChange = {
                    onIntent(UiTaskDetailsActions.OnTaskUpdate(details.copy(isDone = it)))
                }
            )
        }

        //speerate
        // Due date picker
        DateSelector(details.dueDate) {
            onIntent(UiTaskDetailsActions.OnTaskUpdate(details.copy(dueDate = it)))
        }

        PrimaryButton(
            text = "Save",
            enabled = details.title.isNotBlank()
        ) { onIntent(UiTaskDetailsActions.OnTaskSave) }
    }

}
