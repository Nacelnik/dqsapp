package com.training.dietqualityscoring.model

private const val DEFAULT_BENEFIT = -2

enum class MealType(val id: Int, val label: String, val benefits: Array<Int>) {
    VEGETABLES (1, "Vegetables", arrayOf(2, 2, 2, 1, 0, 0)),
    FRUIT(2, "Fruit",  arrayOf(2, 2, 2, 1, 0, 0)),
    LEAN_MEAT(3, "Lean meats & fish", arrayOf(2, 2, 1, 0, 0, -1)),
    NUTS(4, "Nuts & seeds",  arrayOf(2, 2, 1, 0, 0, -1)),
    WHOLE_GRAINS(5, "Whole grains", arrayOf(2, 2, 1, 0, 0, -1)),
    DAIRY(6, "Dairy", arrayOf(1, 1, 1, 0, -1, -2)),
    REFINED_GRAINS(7, "Refined grains", arrayOf(-1, -1, -2)),
    SWEETS(8, "Sweets", arrayOf(-2)),
    FRIED_FOODS(9, "Fried foods", arrayOf(-2)),
    FATTY_PROTEINS(10, "Fatty proteins", arrayOf(-1, -1, -2));

    companion object {
        fun getMealTypeById(id: Int): MealType {
            return values().first { it.id == id }
        }
    }

    fun getBenefit(mealOfDay: Int): Int {
        if (mealOfDay < benefits.size)
            return benefits[mealOfDay]

        return getDefaultBenefit()
    }

    fun getDefaultBenefit(): Int {
        return DEFAULT_BENEFIT;
    }
}