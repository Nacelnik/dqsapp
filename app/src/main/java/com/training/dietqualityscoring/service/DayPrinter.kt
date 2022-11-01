package com.training.dietqualityscoring.service

import com.training.dietqualityscoring.model.Day

class DayPrinter {
    fun printDay(day: Day):String {
        return "On the day of ${day.date} you have eaten ${printMeals(day)}"
    }

    private fun printMeals(day: Day): String {
        var meals = ""
        day.meals.filter { meal -> meal.count > 0 }.forEach{
            meals = "${meals} ${it.count} portions of ${it.mealType.label}, "
        }

        if (meals.isBlank())
            meals = "nothing"

        return meals
    }
}