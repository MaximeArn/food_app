package com.example.food_project.model

import android.text.Html
import com.google.gson.annotations.SerializedName

data class Recipe(
    @SerializedName("pk")
    val pk: Int,

    @SerializedName("title")
    private val titleRaw: String,

    @SerializedName("publisher")
    private val publisherRaw: String,

    @SerializedName("featured_image")
    val featuredImage: String,

    @SerializedName("rating")
    val rating: Int,

    @SerializedName("source_url")
    val sourceUrl: String,

    @SerializedName("description")
    val description: String?,

    @SerializedName("cooking_instructions")
    val cookingInstructions: String?,

    @SerializedName("ingredients")
    val ingredients: List<String>,

    @SerializedName("date_added")
    val dateAdded: String,

    @SerializedName("date_updated")
    val dateUpdated: String,

    @SerializedName("long_date_added")
    val longDateAdded: Long,

    @SerializedName("long_date_updated")
    val longDateUpdated: Long
) {
    val title: String
        get() = Html.fromHtml(titleRaw, Html.FROM_HTML_MODE_LEGACY).toString()

    val publisher: String
        get() = Html.fromHtml(publisherRaw, Html.FROM_HTML_MODE_LEGACY).toString()
}
