package com.training.dietqualityscoring.converter

import androidx.room.TypeConverter

class BenefitsConverter {
    @TypeConverter
    fun getArrayFromString(string: String): Array<Int> {
        return string.split(",").map { it.toInt() }.toTypedArray()
    }

    @TypeConverter
    fun getStringFromArray(array: Array<Int>): String {
        return array.joinToString(",")
    }

}