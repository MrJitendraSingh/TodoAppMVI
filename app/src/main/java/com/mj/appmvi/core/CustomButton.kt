package com.mj.appmvi.core

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.PrimaryKey
import com.mj.appmvi.presentation.ui.theme.PreviewWrapper
import com.mj.appmvi.presentation.ui.theme.White



@Composable
fun PrimaryButton(text: String,
                  enabled: Boolean = true,
                  onClick: () -> Unit){
    Button(onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        enabled = enabled) {
        Text(text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = White)
    }
}

@Preview
@Composable
fun PrimaryButtonPreview(){
    PreviewWrapper {
        PrimaryButton(text = "Save") {

        }
    }
}
