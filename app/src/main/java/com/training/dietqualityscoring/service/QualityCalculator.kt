package com.training.dietqualityscoring.service

import com.training.dietqualityscoring.model.Meals
import com.training.dietqualityscoring.model.VEGETABLES

class QualityCalculator(val meals: Meals) {

    fun calculateScore(): Int {
        var score = 0
        for (i in 0..meals.count) {
            score+= VEGETABLES.getBenefit(i)
        }
        return score
    }
}