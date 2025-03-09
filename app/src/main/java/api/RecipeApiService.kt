package com.example.food_project.api
import com.example.food_project.model.Recipe
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RecipeApiService {
    @GET("recipe/search/")
    suspend fun getRecipes(
        @Header("Authorization") authToken: String,
        @Query("query") query: String = "",
        @Query("page") page: Int = 1
    ): RecipeResponse
}

data class RecipeResponse(
    val results: List<Recipe>
)
