package com.training.dietqualityscoring.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.training.dietqualityscoring.model.Day
import java.time.LocalDate

@Dao
interface DayDao {

    @Query("SELECT * FROM Day")
    fun getAll(): List<Day>

    @Query("SELECT * FROM Day WHERE id = :id")
    fun getById(id: Int): Day?

    @Query("SELECT * FROM Day WHERE date = :date")
    fun getByDate(date: LocalDate): Day?

    //Yeah, this could be better...
    @Query("SELECT nvl(max(id), 0) + 1 FROM Day")
    fun getNextId(): Int

    @Insert
    fun insertAll(vararg day: Day)

    @Delete
    fun delete(day: Day)
}