package com.mj.appmvi.core

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import com.mj.appmvi.R
import com.mj.appmvi.domain.model.TodoItemModel
import com.mj.appmvi.presentation.details.intent.UiTaskDetailsActions


@Preview
@Composable
fun TextInputFieldPreview() {
    TextInputField(value = "Hello", onValueChange = {})
}

@Composable
fun TextInputField(value: String,
                   onValueChange: (String) -> Unit,
                   modifier: Modifier = Modifier,
                   label: String = stringResource(R.string.title),
                   keyboardOptions: KeyboardOptions = KeyboardOptions(
                       capitalization = KeyboardCapitalization.Sentences,
                       imeAction = ImeAction.Next
                   ),
  ){
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = { Text(label) },
        singleLine = true,
        keyboardOptions = keyboardOptions,
        modifier = modifier.fillMaxWidth()
    )
}