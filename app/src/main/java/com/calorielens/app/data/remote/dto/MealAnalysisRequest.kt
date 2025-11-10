package com.calorielens.app.data.remote.dto

data class MealAnalysisRequest(
    val image: String, // Base64 encoded image
    val prompt: String = "Analyze this meal and provide: total calories, macronutrient breakdown (protein, carbs, fats in grams and percentages), and a list of ingredients with their individual calorie counts."
)

