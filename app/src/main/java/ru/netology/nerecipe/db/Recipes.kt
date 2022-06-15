package ru.netology.nerecipe.db

import ru.netology.nerecipe.data.Recipe
import ru.netology.nerecipe.data.RecipeCategories

internal fun RecipeEntity.toModel(): Recipe {

    var resultRecipeCategory = RecipeCategories.Other
    for (part in RecipeCategories.values()){
        if (recipeCategory == part.value) resultRecipeCategory = RecipeCategories.valueOf(part.name)
    }

    return Recipe(
        id = id,
        author = author,
        name = recipeName,
        category = resultRecipeCategory,
        ingredients = ingredients,
        stages = stages,
        published = published,
        videoContent = videoContent,
        likes = likes,
        likedByMe = likedByMe,
        shared = shared,
        sharedByMe = sharedByMe
    )
}

internal fun Recipe.toEntity(): RecipeEntity {

    return RecipeEntity(
        id = id,
        author = author,
        recipeName = name,
        recipeCategory = category.value,
        ingredients = ingredients,
        stages = stages,
        published = published,
        videoContent = videoContent,
        likes = likes,
        likedByMe = likedByMe,
        shared = shared,
        sharedByMe = sharedByMe
    )
}