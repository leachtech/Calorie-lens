package com.calorielens.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

@Composable
fun CalorieLensNavigation() {
    val navController = rememberNavController()
    NavGraph(navController = navController)
}

