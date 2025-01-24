package com.example.yum_recipe_server.recipe.entity

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType

@Document(indexName = "recipes")
data class Recipe(
    @Id
    @Field(type = FieldType.Keyword)
    val id : String,

    @Field(type = FieldType.Text)
    val title : String,

    @Field(type = FieldType.Text)
    val description : String,

    @Field(type = FieldType.Text)
    val amount : String,

    @Field(type = FieldType.Text)
    val cookTime : String,

    @Field(type = FieldType.Text)
    val imageUrl : String,
)

data class Ingredient(
    val name : String,

    val ea : String,
)
