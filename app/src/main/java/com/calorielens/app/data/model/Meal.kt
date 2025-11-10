package com.calorielens.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "meals")
@TypeConverters(IngredientListConverter::class)
data class Meal(
    @PrimaryKey
    val id: String,
    val userId: String,
    val name: String,
    val calories: Int,
    val protein: Double,
    val carbs: Double,
    val fat: Double,
    val imageUri: String? = null,
    val timestamp: Long,
    val mealType: MealType,
    val ingredients: List<Ingredient> = emptyList()
)

enum class MealType {
    BREAKFAST,
    LUNCH,
    DINNER,
    SNACK
}

data class Ingredient(
    val name: String,
    val amount: String,
    val calories: Int
)

class IngredientListConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromIngredientList(value: List<Ingredient>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toIngredientList(value: String): List<Ingredient> {
        val listType = object : TypeToken<List<Ingredient>>() {}.type
        return gson.fromJson(value, listType)
    }
}
