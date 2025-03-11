package com.example.food_project.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food_project.api.RetrofitInstance
import com.example.food_project.model.Recipe
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import android.util.Log

class MainViewModel : ViewModel() {

    var recipes by mutableStateOf<List<Recipe>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    private var currentPage = 1
    var isFetchingMore by mutableStateOf(false)
        private set

    var selectedRecipe by mutableStateOf<Recipe?>(null)
        private set

    var searchQuery by mutableStateOf("")

    init {
        fetchRecipes()
    }

    fun fetchRecipes(query: String = "", isNewSearch: Boolean = false) {
        viewModelScope.launch {
            if (isNewSearch) {
                currentPage = 1
                recipes = emptyList()
            }

            isLoading = true
            errorMessage = null
            try {
                val response = RetrofitInstance.api.getRecipes(
                    authToken = "Token 9c8b06d329136da358c2d00e76946b0111ce2c48",
                    query = query,
                    page = currentPage
                )

                if (response.results.isNotEmpty()) {
                    recipes = if (isNewSearch) response.results else recipes + response.results
                } else if (isNewSearch) {
                    errorMessage = "No recipes found"
                }
            } catch (e: Exception) {
                errorMessage = "Error fetching data: ${e.message}"
                Log.e("API Error", "Error: ${e.message}")
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
                currentPage++
                val response = RetrofitInstance.api.getRecipes(
                    authToken = "Token 9c8b06d329136da358c2d00e76946b0111ce2c48",
                    query = searchQuery,
                    page = currentPage
                )
                if (response.results.isNotEmpty()) {
                    recipes = recipes + response.results
                }
            } catch (e: Exception) {
                errorMessage = "Error fetching more data: ${e.message}"
            } finally {
                isFetchingMore = false
            }
        }
    }

    fun fetchRecipeDetails(recipeId: Int) {
        viewModelScope.launch {
            isLoading = true
            selectedRecipe = null
            try {
                val recipe = RetrofitInstance.api.getRecipeDetails(
                    authToken = "Token 9c8b06d329136da358c2d00e76946b0111ce2c48",
                    id = recipeId
                )
                selectedRecipe = recipe
            } catch (e: Exception) {
                errorMessage = "Error fetching recipe details: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }

    fun searchRecipes(query: String) {
        searchQuery = query
        fetchRecipes(query, isNewSearch = true)
    }

    fun selectCategory(category: String) {
        searchRecipes(category)
    }
}


