package com.example.yum_recipe_server.recipe.service

import com.example.yum_recipe_server.recipe.entity.Recipe
import com.example.yum_recipe_server.recipe.repository.RecipeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RecipeService @Autowired constructor (
    private val recipeRepository: RecipeRepository
) {
    /**
     * 레시피 조회
     */
    suspend fun getAllRecipes() = recipeRepository.findAll()
}