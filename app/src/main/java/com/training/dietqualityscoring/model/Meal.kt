package com.training.dietqualityscoring.model

import androidx.room.Relation

data class Meal(
    @Relation(entity = MealType::class, entityColumn = "mealType", parentColumn = "id") val mealType: MealType,
    var count: Int)

fun EMPTY_MEALS(): List<Meal> {

    return MealType.MEALS.map { meal -> Meal( meal, 0) }
}
