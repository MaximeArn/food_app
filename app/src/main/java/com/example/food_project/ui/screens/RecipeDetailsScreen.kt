package com.example.food_project.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.food_project.viewmodel.MainViewModel

@Composable
fun RecipeDetailsScreen(recipeId: Int, navController: NavController, viewModel: MainViewModel) {
    val recipe by remember { derivedStateOf { viewModel.selectedRecipe } }

    LaunchedEffect(recipeId) {
        viewModel.fetchRecipeDetails(recipeId)
    }

    when {
        viewModel.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        recipe == null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("No recipe found.", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.error)
            }
        }

        else -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(recipe!!.featuredImage),
                        contentDescription = recipe!!.title,
                        modifier = Modifier
                            .fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(text = recipe!!.title, style = MaterialTheme.typography.headlineMedium)
                Text(text = "By ${recipe!!.publisher}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Description: ${recipe!!.description ?: "No description"}")

                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Ingredients:", style = MaterialTheme.typography.titleMedium)
                recipe!!.ingredients.forEach { ingredient ->
                    Text(text = "- $ingredient")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = { navController.popBackStack() }) {
                    Text("Back")
                }
            }
        }
    }
}
