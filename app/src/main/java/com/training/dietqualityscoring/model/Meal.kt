package com.training.dietqualityscoring.model

data class Meal(val mealType: MealType, var count: Int)

fun EMPTY_MEALS(): List<Meal> {

    return MealType.values().map { mealType -> Meal(mealType, 0) }
}
