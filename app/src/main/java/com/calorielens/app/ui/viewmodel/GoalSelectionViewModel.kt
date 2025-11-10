package com.calorielens.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.calorielens.app.data.model.UserGoal
import com.calorielens.app.data.preferences.UserPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class GoalSelectionUiState(
    val selectedGoal: UserGoal? = null,
    val isSaving: Boolean = false
)

class GoalSelectionViewModel(
    private val userPreferences: UserPreferences
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(GoalSelectionUiState())
    val uiState: StateFlow<GoalSelectionUiState> = _uiState.asStateFlow()
    
    fun selectGoal(goal: UserGoal) {
        _uiState.value = _uiState.value.copy(selectedGoal = goal)
    }
    
    fun saveGoal(onComplete: () -> Unit) {
        val selectedGoal = _uiState.value.selectedGoal ?: return
        
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isSaving = true)
            
            try {
                userPreferences.setUserGoal(selectedGoal.name)
                userPreferences.setOnboardingComplete(true)
                
                // Set default goals based on selected goal
                when (selectedGoal) {
                    UserGoal.LOSE_WEIGHT -> {
                        userPreferences.setCaloriesGoal(1800)
                        userPreferences.setProteinGoal(140)
                        userPreferences.setCarbsGoal(200)
                        userPreferences.setFatGoal(60)
                    }
                    UserGoal.GAIN_MUSCLE -> {
                        userPreferences.setCaloriesGoal(2500)
                        userPreferences.setProteinGoal(180)
                        userPreferences.setCarbsGoal(300)
                        userPreferences.setFatGoal(80)
                    }
                    UserGoal.MAINTAIN_WEIGHT -> {
                        userPreferences.setCaloriesGoal(2000)
                        userPreferences.setProteinGoal(150)
                        userPreferences.setCarbsGoal(250)
                        userPreferences.setFatGoal(70)
                    }
                    UserGoal.IMPROVE_ENERGY -> {
                        userPreferences.setCaloriesGoal(2200)
                        userPreferences.setProteinGoal(120)
                        userPreferences.setCarbsGoal(350)
                        userPreferences.setFatGoal(65)
                    }
                }
                
                onComplete()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isSaving = false)
            }
        }
    }
}

