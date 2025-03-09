package com.example.food_project.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.food_project.model.Recipe
import com.example.food_project.viewmodel.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel) {
    if (viewModel.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else if (viewModel.errorMessage != null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = viewModel.errorMessage ?: "Unknown error", color = MaterialTheme.colorScheme.error)
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(viewModel.recipes) { recipe ->
                RecipeCard(recipe)
            }
        }
    }
}

@Composable
fun RecipeCard(recipe: Recipe) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { /* TODO: Navigate to detail screen */ },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val image: Painter = rememberAsyncImagePainter(recipe.featured_image)
            Image(painter = image, contentDescription = recipe.title, modifier = Modifier.size(80.dp))

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(text = recipe.title, style = MaterialTheme.typography.titleMedium)
                Text(text = "Rating: ${recipe.rating}/10", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
