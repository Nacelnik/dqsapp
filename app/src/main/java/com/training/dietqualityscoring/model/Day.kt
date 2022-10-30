package com.training.dietqualityscoring.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class Day(
    @PrimaryKey val id: Int,
    val date: LocalDate,
    val meals : List<Meal>)

