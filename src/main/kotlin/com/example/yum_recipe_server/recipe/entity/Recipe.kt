package com.example.yum_recipe_server.recipe.entity

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "recipes")
data class Recipe(
    @Id
    val id : String,

    val title : String,

    val description : String,

    val amount : String?,

    @JsonProperty("cook_time")
    val cookTime : String?,

    @JsonProperty("image_url")
    val imageUrl : String?,

    val ingredients : List<Ingredient>,

    val steps: List<Step>,
)

data class Ingredient(
    val name : String,

    val ea : String?,
)

data class Step(
    val text : String,

    @JsonProperty("image_url")
    val imageUrl : String?,
)
