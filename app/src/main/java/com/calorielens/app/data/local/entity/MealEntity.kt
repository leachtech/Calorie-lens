package com.calorielens.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.calorielens.app.data.model.Meal

@Entity(tableName = "meals")
data class MealEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val totalCalories: Int,
    val protein: Double,
    val carbs: Double,
    val fat: Double,
    val imageUrl: String?,
    val timestamp: Long
)

fun MealEntity.toMeal(ingredients: List<com.calorielens.app.data.local.entity.IngredientEntity>): Meal {
    return Meal(
        id = id,
        name = name,
        totalCalories = totalCalories,
        protein = protein,
        carbs = carbs,
        fat = fat,
        imageUrl = imageUrl,
        timestamp = timestamp,
        ingredients = ingredients.map { it.toIngredient() }
    )
}

fun Meal.toMealEntity(): MealEntity {
    return MealEntity(
        id = id,
        name = name,
        totalCalories = totalCalories,
        protein = protein,
        carbs = carbs,
        fat = fat,
        imageUrl = imageUrl,
        timestamp = timestamp
    )
}

