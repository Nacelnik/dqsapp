package com.training.dietqualityscoring.model

import androidx.room.Entity
import androidx.room.PrimaryKey

private const val DEFAULT_BENEFIT = -2

@Entity
data class MealType(
    @PrimaryKey val id: Int,
    val name: String,
    val benefits: Array<Int>) {

    companion object {
        private val VEGETABLES = MealType(1, "Vegetables", arrayOf(2, 2, 2, 1, 0, 0))
        private val FRUIT = MealType(2, "Fruit", arrayOf(2, 2, 2, 1, 0, -1))
        private val NUTS = MealType(3, "Nuts", arrayOf(2, 2, 1, 0, 0, -1))
        private val GRAINS = MealType(4, "Grains", arrayOf(2, -1, -1, -2, -2))

        val MEALS = listOf(VEGETABLES, FRUIT, NUTS, GRAINS)
    }

    fun getBenefit(mealOfDay: Int): Int {
        if (mealOfDay < benefits.size)
            return benefits[mealOfDay]

        return getDefaultBenefit()
    }

    fun getDefaultBenefit(): Int {
        return DEFAULT_BENEFIT;
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MealType

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }

}