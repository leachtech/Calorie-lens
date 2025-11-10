package com.calorielens.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "health_metrics")
data class HealthMetric(
    @PrimaryKey
    val id: String,
    val type: MetricType,
    val value: Double,
    val unit: String,
    val timestamp: Long,
    val notes: String? = null,
    val userId: String
)

enum class MetricType {
    CALORIES,
    PROTEIN,
    CARBS,
    FAT,
    WATER,
    STEPS,
    WEIGHT
}

