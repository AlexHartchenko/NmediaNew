package ru.netology.nerecipe.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipes WHERE recipeName LIKE :partName")
    fun getAll(partName: String): LiveData<List<RecipeEntity>>

    @Query("SELECT * FROM recipes WHERE id = :id")
    fun getById(id: Long): RecipeEntity

    @Insert
    fun insert(recipe: RecipeEntity)

    @Update()
    fun update(recipe: RecipeEntity)

    @Query("UPDATE recipes SET recipeName = :content WHERE id = :id")
    fun updateContentById(id: Long, content: String)

    fun save(recipe: RecipeEntity) =
        if (recipe.id == 0L) insert(recipe)
        else update(recipe)

    @Query(
        """
            UPDATE recipes SET
            likes = likes + CASE WHEN likedByMe THEN -1 ELSE 1 END,
            likedByMe = CASE WHEN likedByMe THEN 0 ELSE 1 END
            WHERE id = :id
        """
    )
    fun likeById(id: Long)

    @Query("DELETE FROM recipes WHERE id = :id")
    fun removeById(id: Long)

    @Query(
        """
        UPDATE recipes SET
        sharedByMe = CASE WHEN sharedByMe THEN 0 ELSE 1 END,
        shared = shared + 1
        WHERE id = :id
        """
    )
    fun shareBiId(id: Long)

    @Query("SELECT COUNT(id) FROM recipes LIMIT 1")
    fun hasAnyRecipes(): Boolean

    @Query("SELECT COUNT(id) FROM recipes")
    fun countOfRecipes(): Long
}