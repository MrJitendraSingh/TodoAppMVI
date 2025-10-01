package com.mj.appmvi.core

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.mj.appmvi.domain.model.TodoItemModel
import com.mj.appmvi.presentation.details.intent.UiTaskDetailsActions
import com.mj.appmvi.presentation.ui.theme.PreviewWrapper
import java.util.Calendar
import java.util.Date
import kotlin.collections.set


@Composable
fun DateSelector(dueDate: Long?,
                 onDateChange:(Long) -> Unit){
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
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
                        onDateChange(calendar.timeInMillis)
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
        Text(dueDate?.let { "Due: ${it.millisToDate()}" } ?: "Set Due Date")
    }
}

@Preview
@Composable
fun DateSelectorPreview(){
    PreviewWrapper {
        DateSelector(dueDate = System.currentTimeMillis(), onDateChange = {})
    }
}