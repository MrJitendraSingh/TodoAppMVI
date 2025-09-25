package com.mj.appmvi.core

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mj.appmvi.R
import com.mj.appmvi.presentation.ui.theme.PreviewWrapper

@Preview
@Composable
fun BackTitleBarPreview(){
    PreviewWrapper {
        BackTitleBar("Title"){}
    }
}

@Composable
fun BackTitleBar(title: String, onBack: () -> Unit){
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(60.dp),
        verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = {onBack()}) {
            Icon(Icons.Default.ArrowBack,
                contentDescription = stringResource(R.string.back),
                tint = MaterialTheme.colorScheme.onPrimary)
        }
        Text(text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Preview
@Composable
fun TitleBarPreview(){
    PreviewWrapper {
        TitleBar("Todo App"){

        }
    }
}


@Composable
fun TitleBar(title: String, onClick: () -> Unit){
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(60.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(start = 16.dp)
        )
        IconButton(onClick = {onClick()}) {
            Icon(Icons.Default.Add,
                contentDescription = stringResource(R.string.add),
                tint = MaterialTheme.colorScheme.onPrimary)
        }
    }
}