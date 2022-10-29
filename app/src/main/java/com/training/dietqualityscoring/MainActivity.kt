package com.training.dietqualityscoring

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.training.dietqualityscoring.model.Day
import com.training.dietqualityscoring.ui.theme.DietQualityScoringTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.training.dietqualityscoring.model.EMPTY_MEALS
import com.training.dietqualityscoring.service.QualityCalculator
import java.time.LocalDate


class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    val day = Day(LocalDate.now(), EMPTY_MEALS)

    @RequiresApi(Build.VERSION_CODES.O)
    val calculator = QualityCalculator(day)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DietQualityScoringTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    DietQualityScore(day, calculator)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun DietQualityScore(day: Day, calculator: QualityCalculator) {
    var score by remember {
        mutableStateOf(0)
    }

    Column {
        Text("Diet Quality Score", style = MaterialTheme.typography.h4)
        Text("For day ${day.date}")
        Spacer(modifier = Modifier.size(10.dp))
        Text("Your score is ${score}")
        Column {
            day.meals.forEach { meal ->
                var count by remember {
                    mutableStateOf(meal.count)
                }
                OutlinedButton(onClick = {
                    count = count + 1
                    meal.count = meal.count + 1
                    score = calculator.calculateScore()
                }) {
                    Text("${meal.mealType.name} ${count}")
                }
            }
        }
    }
}