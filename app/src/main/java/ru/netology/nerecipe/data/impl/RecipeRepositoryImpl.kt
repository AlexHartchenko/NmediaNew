package ru.netology.nerecipe.data.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import ru.netology.nerecipe.data.*
import ru.netology.nerecipe.db.RecipeDao
import ru.netology.nerecipe.db.toEntity
import ru.netology.nerecipe.db.toModel

class RecipeRepositoryImpl( private val dao: RecipeDao): RecipeRepository {

    override val data = dao.getAll("%").map { entities ->
        entities.map { it.toModel() }
    }

    init {
        if (!dao.hasAnyRecipes()) {
            val recipe1 = Recipe(
                id = 0L,
                author = "www.RussianFood.com",
                name = "Картофель по-французски.",
                category = RecipeCategories.European,
                ingredients = "Ингредиенты:\n" +
                        "картофель \n" +
                        "\n" +
                        "1 кг\n" +
                        "\n" +
                        "\n" +
                        "сливки \n" +
                        "\n" +
                        "500 мл\n" +
                        "\n" +
                        "или молоко \n" +
                        "\n" +
                        "500 мл\n" +
                        "\n" +
                        "\n" +
                        "сыр твёрдый \n" +
                        "\n" +
                        "100 г\n" +
                        "\n" +
                        "\n" +
                        "масло сливочное \n" +
                        "\n" +
                        "50 г\n" +
                        "\n" +
                        "\n" +
                        "чеснок \n" +
                        "\n" +
                        "2-3 зубчика\n" +
                        "\n" +
                        "\n" +
                        "соль \n" +
                        "\n" +
                        "2 ч. ложки\n" +
                        "\n" +
                        "\n" +
                        "перец чёрный молотый \n" +
                        "\n" +
                        "0.5 ч. ложки\n" +
                        "\n" +
                        "\n" +
                        "орех мускатный \n" +
                        "\n" +
                        "1 щепотка\n",
                stages = listOf(Stage(1,
                    "Подготавливаем продукты для картофеля по-французски.",
                    "https://img1.russianfood.com/dycontent/images_upl/174/sm_173314.jpg"),
                    Stage(2,
                    "Как приготовить картофель по-французски:\n" +
                            "\n" +
                            "один килограмм картофеля чистим, моем и нарезаем пластинками, толщиной 2,5 миллиметра.",
                    "https://img1.russianfood.com/dycontent/images_upl/174/sm_173315.jpg"),
                    Stage(3,
                    "В кастрюлю наливаем 500 миллилитров сливок или молока (можно пополам), ставим на огонь. Добавляем 2 чайные ложки без верха соли, 0,5 чайной ложки черного молотого перца, щепотку мускатного ореха, доводим до кипения.",
                    "https://img1.russianfood.com/dycontent/images_upl/174/big_173316.jpg")
                ),
                videoContent = "https://youtu.be/xOgT2qYAzds",
                published = "21 мая 2020",
                likedByMe = true,
                likes = 1100,
                sharedByMe = false,
                shared = 999997,
            )
            val recipe2 = Recipe(
                id = 0L,
                author = "www.RussianFood.com",
                name = "Бомбейская картошка.",
                category = RecipeCategories.Asian,
                ingredients = "Ингредиенты:\n" +
                        "картофель \n" +
                        "\n" +
                        "800 г\n" +
                        "\n" +
                        "\n" +
                        "помидоры \n" +
                        "\n" +
                        "2-3 шт.\n" +
                        "\n" +
                        "или помидоры замороженные\n" +
                        "\n" +
                        "200 г\n" +
                        "\n" +
                        "или томат-паста \n" +
                        "\n" +
                        "1.5 ч. ложки\n" +
                        "\n" +
                        "\n" +
                        "лук репчатый \n" +
                        "\n" +
                        "1 шт.\n" +
                        "\n" +
                        "\n" +
                        "перец острый \n" +
                        "\n" +
                        "0.5 шт.\n" +
                        "\n" +
                        "\n" +
                        "чеснок \n" +
                        "\n" +
                        "3 зубчика\n" +
                        "\n" +
                        "\n" +
                        "зелень кинзы или петрушки\n" +
                        "\n" +
                        "по вкусу\n" +
                        "\n" +
                        "\n" +
                        "кориандр молотый\n" +
                        "\n" +
                        "1 ч. ложка\n" +
                        "\n" +
                        "\n" +
                        "имбирь молотый сушёный\n" +
                        "\n" +
                        "1 ч. ложка\n" +
                        "\n" +
                        "\n" +
                        "куркума молотая \n" +
                        "\n" +
                        "0.5 ч. ложки\n" +
                        "\n" +
                        "\n" +
                        "карри острый\n" +
                        "\n" +
                        "1 ч. ложка\n" +
                        "\n" +
                        "\n" +
                        "зира молотая (по желанию)\n" +
                        "\n" +
                        "по вкусу\n" +
                        "\n" +
                        "\n" +
                        "соль \n" +
                        "\n" +
                        "по вкусу\n" +
                        "\n" +
                        "\n" +
                        "масло растительное \n" +
                        "\n" +
                        "20 мл\n" +
                        "\n" +
                        "\n",
                stages = listOf(Stage(1,
                    "Картофель очищаем, нарезаем средними кусочками, заливаем водой, доводим до кипения и варим 3-4 минуты, не до готовности.\n" +
                            "Воду сливаем.",
                    "https://img1.russianfood.com/dycontent/images_upl/411/sm_410283.jpg"),
                    Stage(2,
                        "Лук, порезанный кубиками, обжариваем на растительном масле до золотистости.\n" +
                                "Добавляем чеснок, пропущенный через пресс. Нагрев делаем минимальный, чтобы чеснок не сгорел.",
                        "https://img1.russianfood.com/dycontent/images_upl/411/sm_410367.jpg"),
                    Stage(3,
                        "Добавляем имбирь, куркуму, карри и кориандр. Пряности выбирайте по своему вкусу - традиционно сюда в состав входит зира, но у нас в семье любителей нет, поэтому я её не добавляю.",
                        "https://img1.russianfood.com/dycontent/images_upl/411/sm_410284.jpg")
                ),
                videoContent = "https://youtu.be/v8xA-yuZywY",
                published = "21 мая 2020",
                likedByMe = false,
                likes = 1100,
                sharedByMe = false,
                shared = 999997,
            )
//            dao.insert(recipe1.toEntity())
//            dao.insert(recipe2.toEntity())
//            dao.insert(recipeAny.toEntity())
        }
    }

    override fun save(recipe: Recipe) {
        dao.save(recipe.toEntity())
    }

    override fun changeFilter(filter: String): LiveData<List<Recipe>> {
        return dao.getAll(filter).map { entities ->
            entities.map { it.toModel() }
        }
    }

    override fun moveRecipeToPosition(movablePosition: Long, destinationPosition: Long) {
        val destinationRecipe = dao.getById(destinationPosition).toModel()
        val movableRecipe = dao.getById(movablePosition).toModel()
        dao.update(movableRecipe.copy(id = destinationRecipe.id).toEntity())
        dao.update(destinationRecipe.copy(id = movableRecipe.id).toEntity())
    }

    override fun countOfRecipes(): Long {
        return dao.countOfRecipes()
    }

    override fun likeById(recipeId: Long) {
        dao.likeById(recipeId)
    }

    override fun shareBiId(recipeId: Long) {
        dao.shareBiId(recipeId)
    }

    override fun removeById(recipeId: Long) {
        dao.removeById(recipeId)
    }

    override fun getAll(filter: RecipeFilter): LiveData<List<Recipe>> {
        val filterByName = filter.byName
        val filterToDao = if (filterByName == null) "%" else "$filterByName%"
        val liveDataFromDao = dao.getAll(filterToDao).map { entities ->
            entities.map { it.toModel() }
        }
        val liveDataToReturn = liveDataFromDao
            .map { recipes ->
            filterRecipesByCategory(filter.byCategories, recipes)
        }
        return liveDataToReturn
    }

    private fun filterRecipesByCategory(
        categories: List<RecipeCategories>,
        recipes: List<Recipe>): List<Recipe>{
        var result = recipes
        categories.forEach { category ->
            result = result.filter { it.category != category }
        }
        return result
    }
}
