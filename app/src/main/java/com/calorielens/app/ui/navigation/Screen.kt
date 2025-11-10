package com.calorielens.app.ui.navigation

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object WelcomePrivacy : Screen("welcome_privacy")
    object GoalSelection : Screen("goal_selection")
    object MainDashboard : Screen("main_dashboard")
    object VoiceLogging : Screen("voice_logging")
    object FoodScanner : Screen("food_scanner")
    object MealAnalysis : Screen("meal_analysis/{mealId}") {
        fun createRoute(mealId: String) = "meal_analysis/$mealId"
    }
    object Progress : Screen("progress")
    object Social : Screen("social")
    object Profile : Screen("profile")
}

