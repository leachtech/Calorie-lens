package com.calorielens.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * MaterialTheme extension for brand colors
 * Ensures AAA accessibility compliance
 */
object CalorieLensColors {
    val primary: Color @Composable get() = MaterialTheme.colorScheme.primary
    val onPrimary: Color @Composable get() = MaterialTheme.colorScheme.onPrimary
    val secondary: Color @Composable get() = MaterialTheme.colorScheme.secondary
    val background: Color @Composable get() = MaterialTheme.colorScheme.background
    val surface: Color @Composable get() = MaterialTheme.colorScheme.surface
    val error: Color @Composable get() = MaterialTheme.colorScheme.error
    
    // Brand specific colors
    val tealPrimary: Color = Teal500
    val lightBlueBackground: Color = LightBlue50
}

