package com.example.food_project.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import com.example.food_project.viewmodel.MainViewModel

@Composable
fun CategoryFilter(viewModel: MainViewModel) {
    val categories = listOf("Chicken", "Beef", "Soup", "Dessert", "Vegetarian", "French", "Pasta")

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(categories) { category ->
            val isSelected = viewModel.searchQuery == category

            ElevatedButton(
                onClick = { viewModel.selectCategory(category) },
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                    contentColor = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
                ),
                modifier = Modifier
                    .padding(end = 8.dp)
                    .height(40.dp)
            ) {
                Text(
                    text = category,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
