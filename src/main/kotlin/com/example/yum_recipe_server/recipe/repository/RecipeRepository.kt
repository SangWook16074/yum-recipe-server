package com.example.yum_recipe_server.recipe.repository

import com.example.yum_recipe_server.recipe.entity.Recipe
import org.springframework.data.domain.Pageable
import org.springframework.data.elasticsearch.annotations.Query
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository
import reactor.core.publisher.Flux

interface RecipeRepository: ReactiveElasticsearchRepository<Recipe, String> {

    /**
     * 전체 레시피 페이지 네이션 조회 쿼리
     */
//    @Query("{\"from\": 0, \"size\": 20, \"query\": {\"match_all\": {}}}")
//    suspend fun findAll(page: Int, size : Int): Flux<Recipe>
    fun findAllBy(page: Pageable) : Flux<Recipe>
}