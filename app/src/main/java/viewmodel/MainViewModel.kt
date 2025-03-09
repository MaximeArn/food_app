package com.example.food_project.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food_project.api.RetrofitInstance
import com.example.food_project.model.Recipe
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class MainViewModel : ViewModel() {

    var recipes by mutableStateOf<List<Recipe>>(emptyList())
        private set

    var isLoading by mutableStateOf(true)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    init {
        fetchRecipes()
    }

    fun fetchRecipes() {
        viewModelScope.launch {
            isLoading = true
            Log.d("MainViewModel", "Fetching recipes...")

            try {
                val response = RetrofitInstance.api.getRecipes(
                    authToken = "Token 9c8b06d329136da358c2d00e76946b0111ce2c48"
                )
                if (response.results.isNotEmpty()) {
                    recipes = response.results
                } else {
                    errorMessage = "No recipes found"
                }

            } catch (e: Exception) {
                errorMessage = "Error fetching data: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }
}
