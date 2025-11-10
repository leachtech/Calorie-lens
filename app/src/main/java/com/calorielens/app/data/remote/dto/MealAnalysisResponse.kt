package com.calorielens.app.data.remote.dto

data class MealAnalysisResponse(
    val totalCalories: Int,
    val macronutrients: Macronutrients,
    val ingredients: List<IngredientDto>
)

data class Macronutrients(
    val protein: MacronutrientDetail,
    val carbs: MacronutrientDetail,
    val fat: MacronutrientDetail
)

data class MacronutrientDetail(
    val grams: Double,
    val percentage: Double
)

data class IngredientDto(
    val name: String,
    val amount: String,
    val calories: Int
)

