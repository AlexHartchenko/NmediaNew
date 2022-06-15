package ru.netology.nerecipe.db

import androidx.room.*
import ru.netology.nerecipe.data.Stage


@Entity(tableName = "recipes")
@TypeConverters(StageListConverter::class)
class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "author")
    val author: String,
    @ColumnInfo(name = "recipeName")
    val recipeName: String,
    @ColumnInfo(name = "recipeCategory")
    val recipeCategory: String,
    @ColumnInfo(name = "ingredients")
    val ingredients: String,
    @ColumnInfo(name = "stages")
    val stages: List<Stage>,
    @ColumnInfo(name = "videoContent")
    val videoContent: String?,
    @ColumnInfo(name = "published")
    val published: String,
    @ColumnInfo(name = "likedByMe")
    var likedByMe: Boolean = false,
    @ColumnInfo(name = "likes")
    var likes: Int = 0,
    @ColumnInfo(name = "sharedByMe")
    var sharedByMe: Boolean = false,
    @ColumnInfo(name = "shared")
    var shared: Int = 0,
)

object StageListConverter {
    @TypeConverter
    fun toString(stageList: List<Stage>): String? {
        if (stageList.isEmpty()) return null

        val stringList = mutableListOf<String>()
        stageList.forEach {
            stringList.add(it.id.toString())
            stringList.add(it.text)
            stringList.add(it.imageURL)
        }

        return stringList.joinToString("&@#%!*")
    }

    @TypeConverter
    fun toStageList(str: String?): List<Stage>? {
        if (str == null) return null

        val stageList = mutableListOf<Stage>()

        val strList = str.split("&@#%!*")
        for (i in strList.indices step 3) {
            stageList.add(Stage(strList[i].toInt(), strList[i + 1], strList[i + 2]))
        }

        return stageList
    }
}