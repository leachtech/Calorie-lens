package com.calorielens.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val id: String,
    val name: String? = null,
    val email: String? = null,
    val dateOfBirth: Long? = null,
    val goal: UserGoal? = null,
    val dailyCalorieTarget: Int = 2000,
    val dailyProteinTarget: Int = 150,
    val dailyCarbsTarget: Int = 300,
    val dailyFatTarget: Int = 70,
    val isAnonymous: Boolean = true,
    val createdAt: Long = System.currentTimeMillis()
)

enum class UserGoal {
    LOSE_WEIGHT,
    GAIN_MUSCLE,
    MAINTAIN_WEIGHT,
    IMPROVE_ENERGY
}

