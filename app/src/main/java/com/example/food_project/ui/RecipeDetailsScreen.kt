package com.example.food_project.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.food_project.model.Recipe

@Composable
fun RecipeDetailsScreen(recipeId: Int, navController: NavController) {
    val sampleRecipe = Recipe(
        pk = recipeId,
        titleRaw = "Sample Recipe",
        publisherRaw = "Chef John",
        featuredImage = "https://example.com/sample.jpg",
        rating = 10,
        sourceUrl = "https://example.com/recipe",
        description = "A delicious meal to try!",
        cookingInstructions = "Step 1: Do this... Step 2: Do that...",
        ingredients = listOf(
            "Ingredient 1",
            "Ingredient 2",
            "Ingredient 3"
        ),
        dateAdded = "March 2025",
        dateUpdated = "March 2025",
        longDateAdded = 1610000000,
        longDateUpdated = 1610000000
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(sampleRecipe.featuredImage),
            contentDescription = sampleRecipe.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Text(text = sampleRecipe.title, style = MaterialTheme.typography.headlineMedium)
        Text(text = "By ${sampleRecipe.publisher}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Description: ${sampleRecipe.description ?: "No description"}")

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Ingredients:", style = MaterialTheme.typography.titleMedium)
        sampleRecipe.ingredients.forEach { ingredient ->
            Text(text = "- $ingredient")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.popBackStack() }) {
            Text("Back")
        }
    }
}
