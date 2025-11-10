package com.calorielens.app.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.calorielens.app.data.model.Ingredient

@Entity(
    tableName = "ingredients",
    foreignKeys = [
        ForeignKey(
            entity = MealEntity::class,
            parentColumns = ["id"],
            childColumns = ["mealId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class IngredientEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val mealId: String,
    val name: String,
    val amount: String,
    val calories: Int
)

fun IngredientEntity.toIngredient(): Ingredient {
    return Ingredient(
        name = name,
        amount = amount,
        calories = calories
    )
}

fun Ingredient.toIngredientEntity(mealId: String): IngredientEntity {
    return IngredientEntity(
        mealId = mealId,
        name = name,
        amount = amount,
        calories = calories
    )
}

