package com.calorielens.app.data.local.dao

import androidx.room.*
import com.calorielens.app.data.local.entity.IngredientEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IngredientDao {
    @Query("SELECT * FROM ingredients WHERE mealId = :mealId")
    fun getIngredientsForMeal(mealId: String): Flow<List<IngredientEntity>>
    
    @Query("SELECT * FROM ingredients WHERE mealId = :mealId")
    suspend fun getIngredientsForMealSync(mealId: String): List<IngredientEntity>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIngredient(ingredient: IngredientEntity)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIngredients(ingredients: List<IngredientEntity>)
    
    @Delete
    suspend fun deleteIngredient(ingredient: IngredientEntity)
    
    @Query("DELETE FROM ingredients WHERE mealId = :mealId")
    suspend fun deleteIngredientsForMeal(mealId: String)
}

