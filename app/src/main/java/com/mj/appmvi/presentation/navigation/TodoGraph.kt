package com.mj.appmvi.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.mj.appmvi.presentation.details.view.TaskDetailsScreen
import com.mj.appmvi.presentation.tasks.view.TaskListScreen

@Composable
fun TodoGraph(navHostController: NavHostController = rememberNavController(), modifier: Modifier) {

    NavHost(navController = navHostController, startDestination = TodoRoute.TaskListScreen,
        modifier = modifier){
        composable<TodoRoute.TaskListScreen> {
            TaskListScreen { todoRoute ->
                navHostController.navigate(todoRoute)
            }
        }
        composable<TodoRoute.TaskDetailScreen> { backStackEntry ->
            val route: TodoRoute.TaskDetailScreen = backStackEntry.toRoute()
            TaskDetailsScreen( route.id) {
                navHostController.popBackStack()
            }
        }
    }
}