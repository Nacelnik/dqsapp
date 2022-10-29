package com.training.dietqualityscoring.model

private const val DEFAULT_BENEFIT = -2

val VEGETABLES = MealType(arrayOf(2, 2, 2, 1, 0, 0))
val FRUIT = MealType(arrayOf(2, 2, 2, 1, 0, -1))
val NUTS = MealType(arrayOf(2, 2, 1, 0, 0, -1))
val GRAINS = MealType(arrayOf(2, -1, -1, -2, -2))

class MealType(val benefits: Array<Int>) {

    fun getBenefit(mealOfDay: Int): Int {
        if (mealOfDay < benefits.size)
            return benefits[mealOfDay]

        return getDefaultBenefit()
    }

    fun getDefaultBenefit(): Int {
        return DEFAULT_BENEFIT;
    }

}