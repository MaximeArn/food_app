package com.example.food_project.navigation

import androidx.compose.runtime.*
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.food_project.ui.MainScreen
import com.example.food_project.ui.RecipeDetailsScreen
import com.example.food_project.ui.SplashScreen
import com.example.food_project.viewmodel.MainViewModel

@Composable
fun AppNavigation(viewModel: MainViewModel) {
    val navController = rememberNavController()

    if (viewModel.isLoading) {
        SplashScreen()
    } else {
        NavHost(navController = navController, startDestination = "recipes") {
            composable("recipes") {
                MainScreen(viewModel = viewModel, navController = navController)
            }
            composable("recipe/{recipeId}") { backStackEntry ->
                val recipeId = backStackEntry.arguments?.getString("recipeId")?.toIntOrNull()
                if (recipeId != null) {
                    RecipeDetailsScreen(recipeId = recipeId, navController = navController)
                }
            }
        }
    }
}
