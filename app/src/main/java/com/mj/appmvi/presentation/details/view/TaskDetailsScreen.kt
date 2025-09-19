package com.mj.appmvi.presentation.details.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mj.appmvi.R
import com.mj.appmvi.domain.model.TodoItemModel
import com.mj.appmvi.presentation.details.intent.UiTaskDetailsActions
import com.mj.appmvi.presentation.details.model.TaskDetailsViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailsScreen(viewModel: TaskDetailsViewModel = hiltViewModel()) {
    // State variables (if editing, pre-fill them)
    val context = LocalContext.current
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    var title by remember { mutableStateOf(viewModel.initialTask.title) }
    var description by remember { mutableStateOf(viewModel.initialTask.description) }
    var isDone by remember { mutableStateOf(viewModel.initialTask.isDone) }
    var dueDate by remember { mutableStateOf(viewModel.initialTask.dueDate) }

    val dateFormatter = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
    val calendar = Calendar.getInstance()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(if (viewModel.initialTask.id == 0L) stringResource(R.string.add_task) else stringResource(R.string.edit_task)) }
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = uiState.state?.taskDetails?.title ?: "",
                onValueChange = {
                    viewModel.onAction(
                        UiTaskDetailsActions.OnTaskAdded(
                            uiState.state?.taskDetails?.copy(
                                title = it
                            )?: TodoItemModel()
                        )
                    ) },
                label = { Text(stringResource(R.string.title)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = uiState.state?.taskDetails?.description ?: "",
                onValueChange = {
                    viewModel.onAction(
                        UiTaskDetailsActions.OnTaskAdded(
                            uiState.state?.taskDetails?.copy(
                                description = it
                            )?: TodoItemModel()
                        )
                    )
                },
                label = { Text(stringResource(R.string.description)) },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Completed")
                Switch(
                    checked = uiState.state?.taskDetails?.isDone?: false,
                    onCheckedChange = {
                        viewModel.onAction(
                            UiTaskDetailsActions.OnTaskAdded(
                                uiState.state?.taskDetails?.copy(
                                    isDone = it
                                )?: TodoItemModel()
                            )
                        )
                    }
                )
            }

            // Due date picker
            OutlinedButton(onClick = {
                val now = Calendar.getInstance()
                DatePickerDialog(
                    context,
                    { _, year, month, day ->
                        calendar.set(Calendar.YEAR, year)
                        calendar.set(Calendar.MONTH, month)
                        calendar.set(Calendar.DAY_OF_MONTH, day)

                        TimePickerDialog(
                            context,
                            { _, hour, minute ->
                                calendar.set(Calendar.HOUR_OF_DAY, hour)
                                calendar.set(Calendar.MINUTE, minute)
                                dueDate = calendar.timeInMillis
                            },
                            now.get(Calendar.HOUR_OF_DAY),
                            now.get(Calendar.MINUTE),
                            true
                        ).show()
                    },
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH)
                ).show()
            }) {
                Text(dueDate?.let { "Due: ${dateFormatter.format(Date(it))}" } ?: "Set Due Date")
            }

            CancelSave(uiState.state?.taskDetails?: TodoItemModel()) {
                viewModel.onAction(it)
            }
        }
    }
}


@Composable
fun CancelSave(
    todoItemModel: TodoItemModel,
    onClick: (UiTaskDetailsActions) -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OutlinedButton(onClick = {
            onClick(UiTaskDetailsActions.OnBackPressed)
        }) {
            Text("Cancel")
        }
        Button(
            onClick = {
                onClick(UiTaskDetailsActions.OnTaskAdded(todoItemModel))
            },
            enabled = todoItemModel.title.isEmpty()
        ) {
            Text(stringResource(R.string.save))
        }
    }
}
