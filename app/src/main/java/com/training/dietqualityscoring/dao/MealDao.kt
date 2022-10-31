package com.training.dietqualityscoring.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.training.dietqualityscoring.dto.MealDTO
import com.training.dietqualityscoring.model.Meal
import java.time.LocalDate

@Dao
interface MealDao {

    @Query("SELECT * FROM Meal")
    fun getAll(): List<MealDTO>

    @Query("SELECT * FROM Meal WHERE day = :day")
    fun getByDay(day: LocalDate): List<MealDTO>

    @Insert
    fun insertAll(vararg meal: MealDTO)

    @Delete
    fun delete(meal: MealDTO)

    @Query("DELETE FROM Meal WHERE day = :date")
    fun deleteByDate(date: LocalDate)
}