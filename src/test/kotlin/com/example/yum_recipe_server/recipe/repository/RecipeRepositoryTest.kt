package com.example.yum_recipe_server.recipe.repository

import io.kotest.core.extensions.install
import io.kotest.core.spec.style.StringSpec
import io.kotest.extensions.testcontainers.ContainerExtension
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import org.springframework.boot.test.autoconfigure.data.elasticsearch.DataElasticsearchTest
import org.testcontainers.elasticsearch.ElasticsearchContainer

@DataElasticsearchTest
class RecipeRepositoryTest : StringSpec({
    coroutineTestScope = true
    val elasticsearch = install(ContainerExtension(ElasticsearchContainer("docker.elastic.co/elasticsearch/elasticsearch:8.15.5") )) {
        withPassword("1234")
    }

    afterTest {
        clearAllMocks()
    }

    "초기 엘라스틱 서치 컨테이너가 잘 실행된다." {
        elasticsearch.isRunning shouldBe true
    }
})