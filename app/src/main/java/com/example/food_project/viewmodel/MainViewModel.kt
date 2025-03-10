package com.example.food_project.viewmodel

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

    private var currentPage = 1
    var isFetchingMore by mutableStateOf(false)
        private set

    init {
        fetchRecipes()
    }

    fun fetchRecipes() {
        viewModelScope.launch {
            isLoading = true
            try {
                val response = RetrofitInstance.api.getRecipes(
                    authToken = "Token 9c8b06d329136da358c2d00e76946b0111ce2c48",
                    page = currentPage
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

    fun loadMoreRecipes() {
        if (isFetchingMore) return

        viewModelScope.launch {
            isFetchingMore = true
            try {
                val response = RetrofitInstance.api.getRecipes(
                    authToken = "Token 9c8b06d329136da358c2d00e76946b0111ce2c48",
                    page = currentPage + 1
                )
                if (response.results.isNotEmpty()) {
                    recipes = recipes + response.results
                    currentPage++
                }
            } catch (e: Exception) {
                errorMessage = "Error fetching more data: ${e.message}"
            } finally {
                isFetchingMore = false
            }
        }
    }
}

