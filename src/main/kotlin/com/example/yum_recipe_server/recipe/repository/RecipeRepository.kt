package com.example.yum_recipe_server.recipe.repository

import com.example.yum_recipe_server.recipe.entity.Recipe
import org.springframework.data.elasticsearch.annotations.Query
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository

interface RecipeRepository: ReactiveElasticsearchRepository<Recipe, String> {
    /**
     * 전체 레시피 조회 쿼리
     */
    @Query("""
        {
            "query":{
                "match_all":{}                
            }
        }
    """)
    suspend fun findAllRecipes() : List<Recipe>
}