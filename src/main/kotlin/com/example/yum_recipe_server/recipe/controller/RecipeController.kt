package com.example.yum_recipe_server.recipe.controller

import com.example.yum_recipe_server.recipe.entity.Recipe
import com.example.yum_recipe_server.recipe.service.RecipeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/recipe")
class RecipeController @Autowired constructor(
    private val recipeService: RecipeService
) {

    /**
     * 레시피 전체 조회 API
     */
    @GetMapping
    suspend fun getAllRecipes() = recipeService.getAllRecipes()
}