package com.training.dietqualityscoring.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.training.dietqualityscoring.converter.BenefitsConverter
import com.training.dietqualityscoring.dao.DayDao
import com.training.dietqualityscoring.dao.MealTypeDao
import com.training.dietqualityscoring.model.Day
import com.training.dietqualityscoring.model.MealType

@Database(entities = [MealType::class, Day::class], version = 1)
@TypeConverters(BenefitsConverter::class)
abstract class DietQualityDatabase : RoomDatabase() {
    abstract fun mealTypeDao() : MealTypeDao
    abstract fun dayDao() : DayDao
}