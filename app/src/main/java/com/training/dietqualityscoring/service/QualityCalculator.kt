package com.training.dietqualityscoring.service

import com.training.dietqualityscoring.model.Day
import com.training.dietqualityscoring.model.MealType

class QualityCalculator() {

    fun calculateScore(day: Day): Int {
        var score = 0

        day.meals.forEach {
            score += calculateMealTypeBenefit(it.mealType, it.count)
        }

        return score
    }

    private fun calculateMealTypeBenefit(mealType: MealType, portions: Int): Int {
        var score = 0;
        for (i in 0 until portions) {
            score += mealType.getBenefit(i)
        }
        return score
    }
}