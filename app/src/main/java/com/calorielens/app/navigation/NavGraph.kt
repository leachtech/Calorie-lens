package com.calorielens.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.calorielens.app.ui.screens.dashboard.DashboardScreen
import com.calorielens.app.ui.screens.goalselection.GoalSelectionScreen
import com.calorielens.app.ui.screens.onboarding.WelcomeScreen
import com.calorielens.app.ui.screens.scanner.FoodScannerScreen
import com.calorielens.app.ui.screens.voicelogging.VoiceLoggingScreen
import com.calorielens.app.ui.screens.mealanalysis.MealAnalysisScreen

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object GoalSelection : Screen("goal_selection")
    object Dashboard : Screen("dashboard")
    object Scanner : Screen("scanner")
    object VoiceLogging : Screen("voice_logging")
    object MealAnalysis : Screen("meal_analysis/{mealId}") {
        fun createRoute(mealId: String) = "meal_analysis/$mealId"
    }
    object Progress : Screen("progress")
    object Social : Screen("social")
    object Profile : Screen("profile")
}

@Composable
fun CalorieLensNavigation(navController: NavHostController = androidx.navigation.compose.rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Welcome.route
    ) {
        composable(Screen.Welcome.route) {
            WelcomeScreen(
                onGetStarted = {
                    navController.navigate(Screen.GoalSelection.route) {
                        popUpTo(Screen.Welcome.route) { inclusive = true }
                    }
                }
            )
        }
        
        composable(Screen.GoalSelection.route) {
            GoalSelectionScreen(
                onContinue = {
                    navController.navigate(Screen.Dashboard.route) {
                        popUpTo(Screen.GoalSelection.route) { inclusive = true }
                    }
                }
            )
        }
        
        composable(Screen.Dashboard.route) {
            DashboardScreen(
                onNavigateToScanner = {
                    navController.navigate(Screen.Scanner.route)
                },
                onNavigateToVoiceLogging = {
                    navController.navigate(Screen.VoiceLogging.route)
                },
                onNavigateToMealAnalysis = { mealId ->
                    navController.navigate(Screen.MealAnalysis.createRoute(mealId))
                }
            )
        }
        
        composable(Screen.Scanner.route) {
            FoodScannerScreen(
                onBack = { navController.pop() },
                onMealAnalyzed = { mealId ->
                    navController.navigate(Screen.MealAnalysis.createRoute(mealId)) {
                        popUpTo(Screen.Scanner.route)
                    }
                }
            )
        }
        
        composable(Screen.VoiceLogging.route) {
            VoiceLoggingScreen(
                onBack = { navController.pop() },
                onMealLogged = { mealId ->
                    navController.navigate(Screen.MealAnalysis.createRoute(mealId)) {
                        popUpTo(Screen.VoiceLogging.route)
                    }
                }
            )
        }
        
        composable(
            route = Screen.MealAnalysis.route,
            arguments = listOf(
                androidx.navigation.navArgument("mealId") {
                    type = androidx.navigation.NavType.StringType
                }
            )
        ) { backStackEntry ->
            val mealId = backStackEntry.arguments?.getString("mealId") ?: ""
            MealAnalysisScreen(
                mealId = mealId,
                onBack = { navController.pop() }
            )
        }
    }
}

