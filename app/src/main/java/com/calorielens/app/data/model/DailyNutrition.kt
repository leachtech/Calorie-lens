package com.calorielens.app.data.model

data class DailyNutrition(
    val date: Long,
    val calories: Int,
    val caloriesGoal: Int,
    val protein: Double,
    val proteinGoal: Double,
    val carbs: Double,
    val carbsGoal: Double,
    val fat: Double,
    val fatGoal: Double,
    val water: Int, // in ml
    val meals: List<Meal> = emptyList()
)

