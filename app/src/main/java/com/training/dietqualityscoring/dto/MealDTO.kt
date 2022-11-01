package com.training.dietqualityscoring.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "Meal")
data class MealDTO (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val day: LocalDate,
    val mealType: Int,
    val count: Int
)