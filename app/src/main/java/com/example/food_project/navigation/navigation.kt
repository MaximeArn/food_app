package com.example.food_project.navigation

import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.food_project.ui.screens.MainScreen
import com.example.food_project.ui.screens.RecipeDetailsScreen
import com.example.food_project.ui.screens.SplashScreen
import com.example.food_project.viewmodel.MainViewModel

@Composable
fun AppNavigation(viewModel: MainViewModel) {
    val navController = rememberNavController()
    var startDestination by remember { mutableStateOf("splash") }

    LaunchedEffect(viewModel.isLoading) {
        if (!viewModel.isLoading) {
            startDestination = "recipes"
        }
    }

    NavHost(navController = navController, startDestination = startDestination) {
        composable("splash") {
            SplashScreen()
            LaunchedEffect(Unit) {
                if (!viewModel.isLoading) {
                    navController.navigate("recipes") {
                        popUpTo("splash") { inclusive = true } // Remove splash from back stack
                    }
                }
            }
        }

        composable("recipes") {
            MainScreen(viewModel = viewModel, navController = navController)
        }

        composable("recipe/{recipeId}") { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getString("recipeId")?.toIntOrNull()
            if (recipeId != null) {
                RecipeDetailsScreen(recipeId = recipeId, navController = navController, viewModel = viewModel)
            } else {
            }
        }
    }
}
