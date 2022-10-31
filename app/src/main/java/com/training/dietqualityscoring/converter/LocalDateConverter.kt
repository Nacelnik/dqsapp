package com.training.dietqualityscoring.converter

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LocalDateConverter {

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun getStringFromLocalDate(date: LocalDate) : String {
        return DateTimeFormatter.ISO_DATE.format(date)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun getLocalDateFromString(string: String) : LocalDate {
        return LocalDate.parse(string, DateTimeFormatter.ISO_DATE)
    }

}