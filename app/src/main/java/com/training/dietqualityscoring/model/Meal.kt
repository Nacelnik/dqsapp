package com.training.dietqualityscoring.model

data class Meal(val mealType: MealType, var count: Int)

val EMPTY_MEALS = MealType.MEALS.map { meal -> Meal(meal, 0) }
