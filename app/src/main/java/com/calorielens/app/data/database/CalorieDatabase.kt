package com.calorielens.app.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.calorielens.app.data.local.dao.IngredientDao
import com.calorielens.app.data.local.dao.MealDao
import com.calorielens.app.data.local.entity.IngredientEntity
import com.calorielens.app.data.local.entity.MealEntity

@Database(
    entities = [MealEntity::class, IngredientEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CalorieDatabase : RoomDatabase() {
    abstract fun mealDao(): MealDao
    abstract fun ingredientDao(): IngredientDao
    
    companion object {
        @Volatile
        private var INSTANCE: CalorieDatabase? = null
        
        fun getDatabase(context: Context): CalorieDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CalorieDatabase::class.java,
                    "calorie_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

