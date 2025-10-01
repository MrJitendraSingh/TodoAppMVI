package com.mj.appmvi.presentation.tasks.view

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.mj.appmvi.R
import com.mj.appmvi.core.TitleBar
import com.mj.appmvi.domain.model.TodoItemModel
import com.mj.appmvi.presentation.navigation.TodoRoute
import com.mj.appmvi.presentation.tasks.intent.UiTaskListActions
import com.mj.appmvi.presentation.tasks.intent.UiTaskListEffect
import com.mj.appmvi.presentation.tasks.model.TaskListViewModel
import com.mj.appmvi.presentation.ui.theme.PreviewWrapper
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.take
import java.text.SimpleDateFormat
import java.util.Date


@Composable
fun TaskListScreen(viewModel: TaskListViewModel = hiltViewModel(), onNavigate: (TodoRoute) -> Unit) {
    val uiState = viewModel.state.collectAsStateWithLifecycle()
    val state = rememberLazyListState()

    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest {
            when(it){
                UiTaskListEffect.NavigateToTaskCreate -> onNavigate(TodoRoute.TaskDetailScreen())
                is UiTaskListEffect.NavigateToTaskDetail -> onNavigate(TodoRoute.TaskDetailScreen(it.taskId))
            }
        }
    }

    Column(Modifier.fillMaxSize()){
        //sticky
        LazyColumn(state = state) {
            stickyHeader {
                TitleBar(stringResource(R.string.task_list)) {
                    viewModel.onAction(UiTaskListActions.OnAddTaskClicked)
                }
            }

            items(uiState.value.task){ task ->
                TodoItemCard(
                    todo = task,
                    onCheckedChange = { isChecked -> },
                    onClick = {
                        viewModel.onAction(UiTaskListActions.OnTaskStatusChanged(task.id?:0))
                    }
                )
            }
        }
    }
}

@SuppressLint("SimpleDateFormat")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoItemCard(
    todo: TodoItemModel,
    onCheckedChange: (Boolean) -> Unit,
    onClick: () -> Unit
) {
    Card(
        onClick = { onClick() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(modifier = Modifier.weight(1f)) {
                Checkbox(
                    checked = todo.isDone,
                    onCheckedChange = onCheckedChange
                )

                Spacer(modifier = Modifier.width(8.dp))

                Column {
                    Text(
                        text = todo.title,
                        fontSize = 18.sp,
                        textDecoration = if (todo.isDone) TextDecoration.LineThrough else TextDecoration.None
                    )

                    if (todo.description.isNotEmpty()) {
                        Text(
                            text = todo.description,
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    todo.dueDate?.let { due ->
                        Text(
                            text = "Due: ${SimpleDateFormat("dd MMM, HH:mm").format(due)}",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun TaskItemPreview(){
    PreviewWrapper {
        TodoItemCard(todo = TodoItemModel(title = "Test", description = "Test description", dueDate = System.currentTimeMillis()),
            onCheckedChange = {},
            onClick = {})
    }
}

@Preview
@Composable
fun TaskListPreview(){
    PreviewWrapper {
        TaskListScreen(onNavigate = {})
    }
}
