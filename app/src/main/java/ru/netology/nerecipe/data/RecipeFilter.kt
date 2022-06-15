package ru.netology.nerecipe.data

data class RecipeFilter (
    val byName: String?,
    val byCategories: List<RecipeCategories>,
)