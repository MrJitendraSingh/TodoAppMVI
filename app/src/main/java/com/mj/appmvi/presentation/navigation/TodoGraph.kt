package com.mj.appmvi.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.mj.appmvi.presentation.details.view.TaskDetailsScreen
import com.mj.appmvi.presentation.tasks.view.TaskListScreen

@Composable
fun TodoGraph(navHostController: NavHostController = rememberNavController()) {

    NavHost(navController = navHostController, startDestination = TodoRoute.TaskListScreen){
        composable<TodoRoute.TaskListScreen> {
            TaskListScreen(navHostController)
        }
        composable<TodoRoute.TaskDetailScreen> {
            TaskDetailsScreen(navHostController, it.id)
        }
    }
}