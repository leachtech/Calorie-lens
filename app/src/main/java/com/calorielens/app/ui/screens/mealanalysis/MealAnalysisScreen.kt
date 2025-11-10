package com.calorielens.app.ui.screens.mealanalysis

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.calorielens.app.R

@Composable
fun MealAnalysisScreen(
    mealId: String,
    onBack: () -> Unit
) {
    // Sample data - in production, fetch from ViewModel
    val totalCalories = 463
    val protein = MacronutrientDetail(grams = 162.0, percentage = 35.0)
    val carbs = MacronutrientDetail(grams = 185.0, percentage = 40.0)
    val fat = MacronutrientDetail(grams = 116.0, percentage = 25.0)
    
    val ingredients = listOf(
        IngredientItem("Grilled Chicken Breast 150g", 248),
        IngredientItem("Brown Rice 1 cup", 215)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.meal_analysis)) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            // Meal Image placeholder
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colorScheme.surfaceVariant
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    Text("Meal Image")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Total Calories
            Column(
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "$totalCalories",
                    style = MaterialTheme.typography.displayMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = stringResource(R.string.total_calories),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Macronutrient Breakdown
            Text(
                text = stringResource(R.string.macronutrient_breakdown),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            MacroBreakdownCard(
                label = stringResource(R.string.protein),
                grams = protein.grams,
                percentage = protein.percentage
            )

            Spacer(modifier = Modifier.height(12.dp))

            MacroBreakdownCard(
                label = stringResource(R.string.carbs),
                grams = carbs.grams,
                percentage = carbs.percentage
            )

            Spacer(modifier = Modifier.height(12.dp))

            MacroBreakdownCard(
                label = stringResource(R.string.fat),
                grams = fat.grams,
                percentage = fat.percentage
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Ingredients
            Text(
                text = stringResource(R.string.ingredients),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            ingredients.forEach { ingredient ->
                IngredientCard(
                    name = ingredient.name,
                    calories = ingredient.calories
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun MacroBreakdownCard(
    label: String,
    grams: Double,
    percentage: Double
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.titleMedium
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "${grams.toInt()}g",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = "${percentage.toInt()}%",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }
        }
    }
}

@Composable
fun IngredientCard(
    name: String,
    calories: Int
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "$calories kcal",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }
            TextButton(onClick = { }) {
                Text(stringResource(R.string.correct))
            }
        }
    }
}

data class MacronutrientDetail(
    val grams: Double,
    val percentage: Double
)

data class IngredientItem(
    val name: String,
    val calories: Int
)

