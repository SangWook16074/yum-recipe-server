package com.example.yum_recipe_server.recipe.controller

import com.example.yum_recipe_server.recipe.entity.Recipe
import com.example.yum_recipe_server.recipe.service.RecipeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/api/recipes")
class RecipeController @Autowired constructor(
    private val recipeService: RecipeService
) {
    @GetMapping
    private suspend fun getAllRecipesByPage(
        @RequestParam page : Int?,
        @RequestParam size : Int?,
    )
    : Flux<Recipe> {
        return if (page != null && size != null) {
            val result = recipeService.getAllRecipesByPage(page = page, size = size)
            result
        } else {
            val result = recipeService.getAllRecipes()
            result
        }

    }
}