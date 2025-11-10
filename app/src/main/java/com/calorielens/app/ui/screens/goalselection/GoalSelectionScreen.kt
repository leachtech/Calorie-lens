package com.calorielens.app.ui.screens.goalselection

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.calorielens.app.R
import com.calorielens.app.data.model.UserGoal

@Composable
fun GoalSelectionScreen(
    onBack: () -> Unit,
    onContinue: (UserGoal) -> Unit
) {
    var selectedGoal by remember { mutableStateOf<UserGoal?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = stringResource(R.string.goal_selection_title),
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            GoalOption(
                icon = Icons.Default.MonitorWeight,
                title = stringResource(R.string.goal_lose_weight),
                description = stringResource(R.string.goal_lose_weight_desc),
                isSelected = selectedGoal == UserGoal.LOSE_WEIGHT,
                onClick = { selectedGoal = UserGoal.LOSE_WEIGHT }
            )

            Spacer(modifier = Modifier.height(16.dp))

            GoalOption(
                icon = Icons.Default.FitnessCenter,
                title = stringResource(R.string.goal_gain_muscle),
                description = stringResource(R.string.goal_gain_muscle_desc),
                isSelected = selectedGoal == UserGoal.GAIN_MUSCLE,
                onClick = { selectedGoal = UserGoal.GAIN_MUSCLE }
            )

            Spacer(modifier = Modifier.height(16.dp))

            GoalOption(
                icon = Icons.Default.Favorite,
                title = stringResource(R.string.goal_maintain_weight),
                description = stringResource(R.string.goal_maintain_weight_desc),
                isSelected = selectedGoal == UserGoal.MAINTAIN_WEIGHT,
                onClick = { selectedGoal = UserGoal.MAINTAIN_WEIGHT }
            )

            Spacer(modifier = Modifier.height(16.dp))

            GoalOption(
                icon = Icons.Default.Bolt,
                title = stringResource(R.string.goal_improve_energy),
                description = stringResource(R.string.goal_improve_energy_desc),
                isSelected = selectedGoal == UserGoal.IMPROVE_ENERGY,
                onClick = { selectedGoal = UserGoal.IMPROVE_ENERGY }
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    selectedGoal?.let { onContinue(it) }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(bottom = 32.dp),
                enabled = selectedGoal != null,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = stringResource(R.string.continue),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Composable
fun GoalOption(
    icon: ImageVector,
    title: String,
    description: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) {
                MaterialTheme.colorScheme.primaryContainer
            } else {
                MaterialTheme.colorScheme.surface
            }
        ),
        border = if (isSelected) {
            androidx.compose.foundation.BorderStroke(
                2.dp,
                MaterialTheme.colorScheme.primary
            )
        } else null
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(48.dp),
                tint = if (isSelected) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurface
                }
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }
        }
    }
}

