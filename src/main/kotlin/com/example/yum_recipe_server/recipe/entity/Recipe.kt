package com.example.yum_recipe_server.recipe.entity

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType

@Document(indexName = "recipes")
data class Recipe(
    @Id
    val id : String,

    @Field(type = FieldType.Text)
    val title : String,

    @Field(type = FieldType.Text)
    val description : String,

    @Field(type = FieldType.Keyword)
    val amount : String?,

    @JsonProperty("cook_time")
    @Field(type = FieldType.Keyword)
    val cookTime : String?,

    @JsonProperty("image_url")
    @Field(type = FieldType.Text)
    val imageUrl : String?,

    @Field(type = FieldType.Nested)
    val ingredients : List<Ingredient>,

    @Field(type = FieldType.Nested)
    val steps: List<Step>,
)

data class Ingredient(
    @Field(type = FieldType.Text)
    val name : String,

    @Field(type = FieldType.Text)
    val ea : String?,
)

data class Step(
    @Field(type = FieldType.Text)
    val text : String,

    @JsonProperty("image_url")
    @Field(type = FieldType.Text)
    val imageUrl : String?,
)
