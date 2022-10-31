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
import androidx.room.Room
import com.training.dietqualityscoring.database.DietQualityDatabase
import com.training.dietqualityscoring.model.Day
import com.training.dietqualityscoring.service.DataProcessor
import com.training.dietqualityscoring.service.DayPrinter
import com.training.dietqualityscoring.service.QualityCalculator
import com.training.dietqualityscoring.ui.theme.DietQualityScoringTheme
import java.time.LocalDate


class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = Room.databaseBuilder(
            applicationContext,
            DietQualityDatabase::class.java, "dietQuality"
        )
            .allowMainThreadQueries() // TODO: Get rid of this
            .build()

        val dataProcessor = DataProcessor(database)
        val today = getToday(dataProcessor)


        setContent {
            DietQualityScoringTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    DietQualityScore(dataProcessor, today)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DietQualityScore(dataProcessor: DataProcessor, today: Day) {

    var day by remember {
        mutableStateOf(today)
    }

    val year = day.date.year
    val month = day.date.monthValue
    val dayOfMonth = day.date.dayOfMonth

    var date by remember {
        mutableStateOf(today.date.toString())
    }

    val calculator = QualityCalculator()

    var score by remember {
        mutableStateOf(calculator.calculateScore(day))
    }

    val dayPrinter = DayPrinter()

    val datePicker = DatePickerDialog(LocalContext.current, {
            _: DatePicker, _year: Int, _month: Int, _dayOfMonth: Int ->
                val selectedDate = LocalDate.of(_year, _month + 1, _dayOfMonth)
                date = selectedDate.toString()
                dataProcessor.update(day)
                day = getDayForDate(selectedDate, dataProcessor)
                score = calculator.calculateScore(day)
    }, year, month - 1, dayOfMonth)

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
        OutlinedButton(onClick = {dataProcessor.update(day)}) {
            Text("Save today because I don't know any better")
        }
    }

}

fun getDayForDate(selectedDate: LocalDate, dataProcessor: DataProcessor): Day {
    return dataProcessor.getDayByDate(selectedDate)
}

@RequiresApi(Build.VERSION_CODES.O)
fun getToday(dataProcessor: DataProcessor): Day {
    return dataProcessor.getDayByDate(LocalDate.now())
}
