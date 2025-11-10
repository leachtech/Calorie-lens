package com.calorielens.app.data.repository

import com.calorielens.app.data.dao.MealDao
import com.calorielens.app.data.model.Meal
import com.calorielens.app.data.remote.GeminiApiService
import com.calorielens.app.data.remote.dto.MealAnalysisRequest
import kotlinx.coroutines.flow.Flow
import java.util.Calendar

class MealRepository(
    private val mealDao: MealDao,
    private val geminiApiService: GeminiApiService,
    private val apiKey: String
) {
    fun getMealsByUser(userId: String): Flow<List<Meal>> = mealDao.getMealsByUser(userId)

    fun getMealsForToday(userId: String): Flow<List<Meal>> {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        val startOfDay = calendar.timeInMillis

        calendar.add(Calendar.DAY_OF_MONTH, 1)
        val endOfDay = calendar.timeInMillis

        return mealDao.getMealsForDay(userId, startOfDay, endOfDay)
    }

    suspend fun insertMeal(meal: Meal) {
        mealDao.insertMeal(meal)
    }

    suspend fun deleteMeal(mealId: String) {
        mealDao.deleteMeal(mealId)
    }

    fun getTotalCaloriesForToday(userId: String): Flow<Int?> {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        val startOfDay = calendar.timeInMillis

        calendar.add(Calendar.DAY_OF_MONTH, 1)
        val endOfDay = calendar.timeInMillis

        return mealDao.getTotalCaloriesForDay(userId, startOfDay, endOfDay)
    }

    fun getTotalProteinForToday(userId: String): Flow<Double?> {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        val startOfDay = calendar.timeInMillis

        calendar.add(Calendar.DAY_OF_MONTH, 1)
        val endOfDay = calendar.timeInMillis

        return mealDao.getTotalProteinForDay(userId, startOfDay, endOfDay)
    }

    fun getTotalCarbsForToday(userId: String): Flow<Double?> {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        val startOfDay = calendar.timeInMillis

        calendar.add(Calendar.DAY_OF_MONTH, 1)
        val endOfDay = calendar.timeInMillis

        return mealDao.getTotalCarbsForDay(userId, startOfDay, endOfDay)
    }

    fun getTotalFatForToday(userId: String): Flow<Double?> {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        val startOfDay = calendar.timeInMillis

        calendar.add(Calendar.DAY_OF_MONTH, 1)
        val endOfDay = calendar.timeInMillis

        return mealDao.getTotalFatForDay(userId, startOfDay, endOfDay)
    }

    suspend fun analyzeMealImage(imageBase64: String): com.calorielens.app.data.remote.dto.MealAnalysisResponse {
        return geminiApiService.analyzeMeal(
            apiKey = "Bearer $apiKey",
            request = MealAnalysisRequest(image = imageBase64)
        )
    }
}
