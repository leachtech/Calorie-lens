package com.calorielens.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.calorielens.app.data.repository.MealRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class FoodScannerUiState {
    object Idle : FoodScannerUiState()
    object Capturing : FoodScannerUiState()
    object Analyzing : FoodScannerUiState()
    data class Success(val mealId: String) : FoodScannerUiState()
    data class Error(val message: String) : FoodScannerUiState()
}

class FoodScannerViewModel(
    private val mealRepository: MealRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow<FoodScannerUiState>(FoodScannerUiState.Idle)
    val uiState: StateFlow<FoodScannerUiState> = _uiState.asStateFlow()
    
    fun analyzeImage(imageBase64: String) {
        viewModelScope.launch {
            _uiState.value = FoodScannerUiState.Analyzing
            
            mealRepository.analyzeMealFromImage(imageBase64)
                .onSuccess { response ->
                    mealRepository.saveMeal(response.meal)
                    _uiState.value = FoodScannerUiState.Success(response.meal.id)
                }
                .onFailure { e ->
                    _uiState.value = FoodScannerUiState.Error(
                        e.message ?: "Failed to analyze meal"
                    )
                }
        }
    }
    
    fun resetState() {
        _uiState.value = FoodScannerUiState.Idle
    }
}

