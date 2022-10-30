package com.training.dietqualityscoring

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.widget.DatePicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.room.Database
import androidx.room.Room
import com.training.dietqualityscoring.database.DietQualityDatabase
import com.training.dietqualityscoring.model.Day
import com.training.dietqualityscoring.model.EMPTY_MEALS
import com.training.dietqualityscoring.service.DayPrinter
import com.training.dietqualityscoring.service.QualityCalculator
import com.training.dietqualityscoring.ui.theme.DietQualityScoringTheme
import java.time.LocalDate


class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DietQualityScoringTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    DietQualityScore()
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DietQualityScore() {
    val context = LocalContext.current

    val database = Room.databaseBuilder(context,
        DietQualityDatabase::class.java, "dietQuality"
        ).build()


    val today = getToday(database)
    var day by remember {
        mutableStateOf(today)
    }


    val year = day.date.year
    val month = day.date.monthValue
    val dayOfMonth = day.date.dayOfMonth

    var date by remember {
        mutableStateOf(today.date.toString())
    }

    var score by remember {
        mutableStateOf(0)
    }

    val calculator = QualityCalculator()
    val dayPrinter = DayPrinter()

    val datePicker = DatePickerDialog(context, {
            _: DatePicker, _year: Int, _month: Int, _dayOfMonth: Int ->
                val selectedDate = LocalDate.of(_year, _month, _dayOfMonth)
                date = selectedDate.toString()
                database.dayDao().insertAll(day)
                day = getDayForDate(selectedDate, database)
                score = calculator.calculateScore(day)
    }, year, month, dayOfMonth)

    Column {
        Text("Diet Quality Score", style = MaterialTheme.typography.h4)
        Text(dayPrinter.printDay(day))
        Spacer(modifier = Modifier.size(10.dp))
        Text("Your score is ${score}")
        Spacer(modifier = Modifier.size(10.dp))

        Column {
            day.meals.forEach { meal ->
                OutlinedButton(onClick = {
                    meal.count = meal.count + 1
                    score = calculator.calculateScore(day)
                }) {
                    Text(meal.mealType.name)
                }
            }
        }
        OutlinedButton(onClick = { datePicker.show() }) {
            Text("Pick a date :${date}")
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun getToday(database: DietQualityDatabase): Day
{
    return getDayForDate(LocalDate.now(), database)
}

private fun getDayForDate(date: LocalDate, database: DietQualityDatabase): Day
{
    var day = database.dayDao().getByDate(date)
    if (day == null)
        day = Day(database.dayDao().getNextId(), date, EMPTY_MEALS())
    return day
}