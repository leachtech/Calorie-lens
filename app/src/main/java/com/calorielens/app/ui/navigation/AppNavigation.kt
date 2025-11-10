package com.calorielens.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.calorielens.app.ui.screens.dashboard.DashboardScreen
import com.calorielens.app.ui.screens.goalselection.GoalSelectionScreen
import com.calorielens.app.ui.screens.mealanalysis.MealAnalysisScreen
import com.calorielens.app.ui.screens.scanner.FoodScannerScreen
import com.calorielens.app.ui.screens.voicelogging.VoiceLoggingScreen
import com.calorielens.app.ui.screens.welcome.WelcomeScreen
import com.calorielens.app.ui.screens.welcome.WelcomePrivacyScreen

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Welcome.route
    ) {
        composable(Screen.Welcome.route) {
            WelcomeScreen(
                onGetStarted = {
                    navController.navigate(Screen.WelcomePrivacy.route)
                }
            )
        }

        composable(Screen.WelcomePrivacy.route) {
            WelcomePrivacyScreen(
                onBack = { navController.popBackStack() },
                onCreateAccount = {
                    navController.navigate(Screen.GoalSelection.route) {
                        popUpTo(Screen.Welcome.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.GoalSelection.route) {
            GoalSelectionScreen(
                onBack = { navController.popBackStack() },
                onContinue = {
                    navController.navigate(Screen.MainDashboard.route) {
                        popUpTo(Screen.Welcome.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.MainDashboard.route) {
            DashboardScreen(
                onNavigateToScanner = {
                    navController.navigate(Screen.FoodScanner.route)
                },
                onNavigateToVoice = {
                    navController.navigate(Screen.VoiceLogging.route)
                },
                onNavigateToMealAnalysis = { mealId ->
                    navController.navigate(Screen.MealAnalysis.createRoute(mealId))
                }
            )
        }

        composable(Screen.FoodScanner.route) {
            FoodScannerScreen(
                onBack = { navController.popBackStack() },
                onMealAnalyzed = { mealId ->
                    navController.navigate(Screen.MealAnalysis.createRoute(mealId)) {
                        popUpTo(Screen.MainDashboard.route)
                    }
                }
            )
        }

        composable(Screen.VoiceLogging.route) {
            VoiceLoggingScreen(
                onBack = { navController.popBackStack() },
                onMealAnalyzed = { mealId ->
                    navController.navigate(Screen.MealAnalysis.createRoute(mealId)) {
                        popUpTo(Screen.MainDashboard.route)
                    }
                }
            )
        }

        composable(Screen.MealAnalysis.route) { backStackEntry ->
            val mealId = backStackEntry.arguments?.getString("mealId") ?: ""
            MealAnalysisScreen(
                mealId = mealId,
                onBack = { navController.popBackStack() }
            )
        }
    }
}

