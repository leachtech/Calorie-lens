package com.calorielens.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.calorielens.app.data.repository.MealRepository
import com.calorielens.app.data.repository.UserRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import kotlinx.coroutines.flow.combine

data class DashboardUiState(
    val userName: String = "Alex",
    val currentDate: String = "",
    val caloriesConsumed: Int = 0,
    val caloriesTarget: Int = 2000,
    val proteinConsumed: Double = 0.0,
    val proteinTarget: Int = 150,
    val carbsConsumed: Double = 0.0,
    val carbsTarget: Int = 300,
    val fatConsumed: Double = 0.0,
    val fatTarget: Int = 70,
    val waterIntake: Int = 0,
    val recentMeals: List<String> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class DashboardViewModel(
    private val userRepository: UserRepository,
    private val mealRepository: MealRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    private val userId = "default_user" // In production, get from auth

    init {
        loadDashboardData()
    }

    private fun loadDashboardData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            try {
                val user = userRepository.getCurrentUser().first()
                user?.let {
                    _uiState.update { state ->
                        state.copy(
                            userName = it.name ?: "Alex",
                            caloriesTarget = it.dailyCalorieTarget,
                            proteinTarget = it.dailyProteinTarget,
                            carbsTarget = it.dailyCarbsTarget,
                            fatTarget = it.dailyFatTarget
                        )
                    }
                }

                // Combine all flows
                combine(
                    mealRepository.getTotalCaloriesForToday(userId),
                    mealRepository.getTotalProteinForToday(userId),
                    mealRepository.getTotalCarbsForToday(userId),
                    mealRepository.getTotalFatForToday(userId),
                    mealRepository.getMealsForToday(userId)
                ) { calories, protein, carbs, fat, meals ->
                    _uiState.update { state ->
                        state.copy(
                            caloriesConsumed = calories ?: 0,
                            proteinConsumed = protein ?: 0.0,
                            carbsConsumed = carbs ?: 0.0,
                            fatConsumed = fat ?: 0.0,
                            recentMeals = meals.map { it.name },
                            isLoading = false
                        )
                    }
                }.collect()

                // Format date
                val calendar = Calendar.getInstance()
                val dayOfWeek = calendar.getDisplayName(
                    Calendar.DAY_OF_WEEK,
                    Calendar.LONG,
                    Locale.getDefault()
                )
                val day = calendar.get(Calendar.DAY_OF_MONTH)
                val month = calendar.getDisplayName(
                    Calendar.MONTH,
                    Calendar.LONG,
                    Locale.getDefault()
                )
                _uiState.update { it.copy(currentDate = "$dayOfWeek, $day $month") }

            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }
}
