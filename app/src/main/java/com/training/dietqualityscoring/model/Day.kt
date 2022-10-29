package com.training.dietqualityscoring.model

import java.time.LocalDate

data class Day(val date: LocalDate, val meals : List<Meal>)

