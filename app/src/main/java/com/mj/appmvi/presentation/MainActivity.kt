package com.mj.appmvi.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.mj.appmvi.core.ui.theme.AppMVITheme
import com.mj.appmvi.presentation.navigation.TodoGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppMVITheme {
                Scaffold(modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->
                    TodoGraph(modifier = Modifier.Companion.padding(innerPadding))
                }
            }
        }
    }
}