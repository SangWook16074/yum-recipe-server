package com.example.yum_recipe_server.recipe.repository

import com.example.yum_recipe_server.recipe.entity.Recipe
import org.springframework.data.domain.Pageable
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository
import reactor.core.publisher.Flux

interface RecipeRepository: ReactiveElasticsearchRepository<Recipe, String> {

    /**
     * 전체 레시피 페이지 네이션 조회 쿼리
     */
    fun findAllBy(page: Pageable) : Flux<Recipe>
}