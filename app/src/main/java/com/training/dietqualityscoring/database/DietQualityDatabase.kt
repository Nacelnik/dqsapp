package com.training.dietqualityscoring.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.training.dietqualityscoring.converter.LocalDateConverter
import com.training.dietqualityscoring.dao.MealDao
import com.training.dietqualityscoring.dto.MealDTO

@Database(entities = [MealDTO::class], version = 1, exportSchema = false)
@TypeConverters(LocalDateConverter::class)
abstract class DietQualityDatabase : RoomDatabase() {
    abstract fun mealDao(): MealDao
}