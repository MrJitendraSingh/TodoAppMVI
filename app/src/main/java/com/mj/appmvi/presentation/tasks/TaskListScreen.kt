package com.mj.appmvi.presentation.tasks

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mj.appmvi.R
import com.mj.appmvi.domain.model.TodoItemModel
import java.text.SimpleDateFormat

@Composable
fun TaskListScreen() {
    val state = rememberLazyListState()
    Column(Modifier.fillMaxSize()){
        Row(Modifier
            .fillMaxWidth()
            .height(60.dp)) {
            Text(stringResource(R.string.task_list),
                style = MaterialTheme.typography.titleLarge)
        }

        LazyColumn(state = state) {
            items(10) { index ->
                TodoItemCard(
                    TodoItemModel().copy(title = index.toString()),
                    onCheckedChange = { isChecked -> },
                    onClick = {  }
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
            .padding(8.dp),
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