package com.calorielens.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.calorielens.app.data.repository.MealRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class VoiceLoggingUiState {
    object Idle : VoiceLoggingUiState()
    object Listening : VoiceLoggingUiState()
    object Processing : VoiceLoggingUiState()
    data class Success(val mealId: String) : VoiceLoggingUiState()
    data class Error(val message: String) : VoiceLoggingUiState()
}

class VoiceLoggingViewModel(
    private val mealRepository: MealRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow<VoiceLoggingUiState>(VoiceLoggingUiState.Idle)
    val uiState: StateFlow<VoiceLoggingUiState> = _uiState.asStateFlow()
    
    fun processVoiceText(text: String) {
        viewModelScope.launch {
            _uiState.value = VoiceLoggingUiState.Processing
            
            mealRepository.analyzeMealFromText(text)
                .onSuccess { response ->
                    mealRepository.saveMeal(response.meal)
                    _uiState.value = VoiceLoggingUiState.Success(response.meal.id)
                }
                .onFailure { e ->
                    _uiState.value = VoiceLoggingUiState.Error(
                        e.message ?: "Failed to process voice input"
                    )
                }
        }
    }
    
    fun startListening() {
        _uiState.value = VoiceLoggingUiState.Listening
    }
    
    fun pauseListening() {
        _uiState.value = VoiceLoggingUiState.Idle
    }
    
    fun resetState() {
        _uiState.value = VoiceLoggingUiState.Idle
    }
}

