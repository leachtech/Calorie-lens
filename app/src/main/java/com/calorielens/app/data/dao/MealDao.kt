package com.calorielens.app.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.calorielens.app.data.model.Meal
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {
    @Query("SELECT * FROM meals WHERE userId = :userId ORDER BY timestamp DESC")
    fun getMealsByUser(userId: String): Flow<List<Meal>>

    @Query("SELECT * FROM meals WHERE userId = :userId AND timestamp >= :startOfDay AND timestamp < :endOfDay ORDER BY timestamp DESC")
    fun getMealsForDay(userId: String, startOfDay: Long, endOfDay: Long): Flow<List<Meal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(meal: Meal)

    @Query("DELETE FROM meals WHERE id = :mealId")
    suspend fun deleteMeal(mealId: String)

    @Query("SELECT SUM(calories) FROM meals WHERE userId = :userId AND timestamp >= :startOfDay AND timestamp < :endOfDay")
    fun getTotalCaloriesForDay(userId: String, startOfDay: Long, endOfDay: Long): Flow<Int?>

    @Query("SELECT SUM(protein) FROM meals WHERE userId = :userId AND timestamp >= :startOfDay AND timestamp < :endOfDay")
    fun getTotalProteinForDay(userId: String, startOfDay: Long, endOfDay: Long): Flow<Double?>

    @Query("SELECT SUM(carbs) FROM meals WHERE userId = :userId AND timestamp >= :startOfDay AND timestamp < :endOfDay")
    fun getTotalCarbsForDay(userId: String, startOfDay: Long, endOfDay: Long): Flow<Double?>

    @Query("SELECT SUM(fat) FROM meals WHERE userId = :userId AND timestamp >= :startOfDay AND timestamp < :endOfDay")
    fun getTotalFatForDay(userId: String, startOfDay: Long, endOfDay: Long): Flow<Double?>
}

