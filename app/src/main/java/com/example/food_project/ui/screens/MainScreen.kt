package com.example.food_project.ui.screens

import RecipeCard
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.food_project.viewmodel.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel, navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        // Search bar at the top
        com.example.food_project.ui.components.SearchBar(
            query = searchQuery,
            onQueryChanged = { searchQuery = it })

        if (viewModel.errorMessage != null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = viewModel.errorMessage ?: "Unknown error", color = MaterialTheme.colorScheme.error)
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                itemsIndexed(viewModel.recipes) { index, recipe ->
                    RecipeCard(recipe = recipe, onClick = {
                        navController.navigate("recipe/${recipe.pk}")
                    })

                    if (index == viewModel.recipes.size - 1) {
                        LaunchedEffect(Unit) {
                            viewModel.loadMoreRecipes()
                        }
                    }
                }

                if (viewModel.isFetchingMore) {
                    item {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}
