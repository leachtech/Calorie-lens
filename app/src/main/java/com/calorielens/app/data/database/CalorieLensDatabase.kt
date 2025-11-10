package com.calorielens.app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.calorielens.app.data.dao.MealDao
import com.calorielens.app.data.dao.UserDao
import com.calorielens.app.data.model.Meal
import com.calorielens.app.data.model.User

@Database(
    entities = [User::class, Meal::class],
    version = 1,
    exportSchema = false
)
abstract class CalorieLensDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun mealDao(): MealDao
}

