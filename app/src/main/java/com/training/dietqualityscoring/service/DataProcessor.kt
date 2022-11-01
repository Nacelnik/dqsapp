package com.training.dietqualityscoring.service

import com.training.dietqualityscoring.database.DietQualityDatabase
import com.training.dietqualityscoring.dto.MealDTO
import com.training.dietqualityscoring.model.Day
import com.training.dietqualityscoring.model.EMPTY_MEALS
import com.training.dietqualityscoring.model.Meal
import com.training.dietqualityscoring.model.MealType
import java.time.LocalDate

class DataProcessor(val database: DietQualityDatabase) {

    fun update(day: Day)
    {
        delete(day)
        insert(day)
    }

    fun delete(day: Day) {
        database.mealDao().deleteByDate(day.date)
    }

    fun insert(day: Day) {
        toDTO(day).forEach{
            database.mealDao().insertAll(it)
        }
    }

    fun getDayByDate(date: LocalDate): Day {
        val meals = database.mealDao().getByDay(date)

        val processedMeals: List<Meal>
        if (meals.isNotEmpty())
            processedMeals = mealsFromDTO(meals)
        else
            processedMeals = EMPTY_MEALS()

        return Day(date, processedMeals)
    }

    private fun toDTO(day: Day): List<MealDTO> {
        val meals = ArrayList<MealDTO>();
        day.meals.forEach{meal -> meals.add(MealDTO(0, day.date, meal.mealType.id, meal.count))}
        return meals;
    }

    private fun mealsFromDTO(meals: List<MealDTO>): List<Meal> {
        val processed = ArrayList<Meal>()
        meals.forEach{
            meal -> processed.add(Meal(MealType.getMealTypeById(meal.mealType), meal.count))
        }
        return processed
    }

}