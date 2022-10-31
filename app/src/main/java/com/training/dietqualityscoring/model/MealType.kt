package com.training.dietqualityscoring.model

private const val DEFAULT_BENEFIT = -2

data class MealType(
    val name: String,
    val benefits: Array<Int>) {

    companion object {
        private val VEGETABLES = MealType("Vegetables", arrayOf(2, 2, 2, 1, 0, 0))
        private val FRUIT = MealType("Fruit", arrayOf(2, 2, 2, 1, 0, -1))
        private val NUTS = MealType( "Nuts", arrayOf(2, 2, 1, 0, 0, -1))
        private val GRAINS = MealType( "Grains", arrayOf(2, -1, -1, -2, -2))

        val MEALS = listOf(VEGETABLES, FRUIT, NUTS, GRAINS)

        fun getMealTypeByName(mealType: String): MealType {
            return MEALS.filter { it -> it.name == mealType }.first()
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