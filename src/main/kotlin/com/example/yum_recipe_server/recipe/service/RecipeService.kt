package com.example.yum_recipe_server.recipe.service

import com.example.yum_recipe_server.recipe.entity.Recipe
import com.example.yum_recipe_server.recipe.repository.RecipeRepository
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class RecipeService @Autowired constructor (
    private val recipeRepository: RecipeRepository
) {
    /**
     * 레시피 조회
     */
    suspend fun getAllRecipes() : Flux<Recipe> = recipeRepository.findAll()

    /**
     * 레시피 Pagenation 조회
     */
    suspend fun getAllRecipesByPage(page: Int, size : Int) : Flux<Recipe> {
        val pageable : Pageable = PageRequest.of(page, size)
        val result = recipeRepository.findAllBy(page = pageable)
        return result
    }
}