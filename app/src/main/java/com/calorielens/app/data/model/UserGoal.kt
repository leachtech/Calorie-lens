package com.calorielens.app.data.model

enum class UserGoal(val title: String, val description: String) {
    LOSE_WEIGHT(
        title = "Lose Weight",
        description = "Set a calorie deficit to sustainably reduce body weight."
    ),
    GAIN_MUSCLE(
        title = "Gain Muscle",
        description = "Focus on a protein-rich diet and a calorie surplus to build lean mass."
    ),
    MAINTAIN_WEIGHT(
        title = "Maintain Weight",
        description = "Learn your maintenance calories to stay at your current weight."
    ),
    IMPROVE_ENERGY(
        title = "Improve Energy",
        description = "Track macronutrients to optimize your daily energy levels."
    )
}

