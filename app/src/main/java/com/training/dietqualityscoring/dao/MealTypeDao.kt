package com.training.dietqualityscoring.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.training.dietqualityscoring.model.MealType

@Dao
interface MealTypeDao {
    @Query("SELECT * FROM MealType")
    fun getAll(): List<MealType>

    @Query("SELECT * FROM MealType WHERE id = :id")
    fun findById(id : Int): MealType?

    @Query("SELECT * FROM MealType WHERE name = :name LIMIT 1")
    fun findByName(name: String): MealType?

    @Insert
    fun insertAll(vararg mealType: MealType)

    @Delete
    fun delete(mealType: MealType)
}