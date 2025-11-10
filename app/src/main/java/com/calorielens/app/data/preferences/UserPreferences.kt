package com.calorielens.app.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

class UserPreferences(private val context: Context) {
    companion object {
        private val IS_ONBOARDING_COMPLETE = booleanPreferencesKey("is_onboarding_complete")
        private val USER_NAME = stringPreferencesKey("user_name")
        private val USER_GOAL = stringPreferencesKey("user_goal")
        private val CALORIES_GOAL = intPreferencesKey("calories_goal")
        private val PROTEIN_GOAL = intPreferencesKey("protein_goal")
        private val CARBS_GOAL = intPreferencesKey("carbs_goal")
        private val FAT_GOAL = intPreferencesKey("fat_goal")
    }
    
    val isOnboardingComplete: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[IS_ONBOARDING_COMPLETE] ?: false
    }
    
    suspend fun setOnboardingComplete(complete: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_ONBOARDING_COMPLETE] = complete
        }
    }
    
    val userName: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[USER_NAME] ?: "Alex"
    }
    
    suspend fun setUserName(name: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_NAME] = name
        }
    }
    
    val userGoal: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[USER_GOAL] ?: ""
    }
    
    suspend fun setUserGoal(goal: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_GOAL] = goal
        }
    }
    
    val caloriesGoal: Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[CALORIES_GOAL] ?: 2000
    }
    
    suspend fun setCaloriesGoal(goal: Int) {
        context.dataStore.edit { preferences ->
            preferences[CALORIES_GOAL] = goal
        }
    }
    
    val proteinGoal: Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[PROTEIN_GOAL] ?: 150
    }
    
    suspend fun setProteinGoal(goal: Int) {
        context.dataStore.edit { preferences ->
            preferences[PROTEIN_GOAL] = goal
        }
    }
    
    val carbsGoal: Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[CARBS_GOAL] ?: 300
    }
    
    suspend fun setCarbsGoal(goal: Int) {
        context.dataStore.edit { preferences ->
            preferences[CARBS_GOAL] = goal
        }
    }
    
    val fatGoal: Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[FAT_GOAL] ?: 70
    }
    
    suspend fun setFatGoal(goal: Int) {
        context.dataStore.edit { preferences ->
            preferences[FAT_GOAL] = goal
        }
    }
}

