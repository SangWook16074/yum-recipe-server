package com.example.yum_recipe_server.recipe.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "recipes")
data class Recipe(
    @Id
    val id : String,

    val title : String,

    val description : String,

    val amount : String?,

    val cookTime : String?,

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

    val imageUrl : String?,
)
