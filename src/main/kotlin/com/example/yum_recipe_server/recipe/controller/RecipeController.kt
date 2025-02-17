package com.example.yum_recipe_server.recipe.controller

import com.example.yum_recipe_server.recipe.entity.Recipe
import com.example.yum_recipe_server.recipe.service.RecipeService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux

@Tag(name = "Ingredient 서버" ,description = "재료 관련 Api",)
@RestController
@RequestMapping("/api/recipes")
class RecipeController @Autowired constructor(
    private val recipeService: RecipeService
) {

    /**
     * 레시피 검색 Api
     *
     * 사용자는 쿼리파라미터를 통해서
     * 페이지네이션 혹은 전체 레시피를 검색할 수 있습니다.
     */
    @Operation(description = "레시피 검색 Api")
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