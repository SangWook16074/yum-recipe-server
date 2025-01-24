package com.example.yum_recipe_server.recipe.repository

import com.example.yum_recipe_server.recipe.entity.Recipe
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository

interface RecipeRepository: ReactiveElasticsearchRepository<Recipe, String> {
//    suspend fun findAllRecipes() : List<Recipe>
}