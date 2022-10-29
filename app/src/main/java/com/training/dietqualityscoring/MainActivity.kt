package com.training.dietqualityscoring

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.training.dietqualityscoring.model.Meals
import com.training.dietqualityscoring.service.QualityCalculator
import com.training.dietqualityscoring.ui.theme.DietQualityScoringTheme


class MainActivity : ComponentActivity() {
    val model = Meals(0)
    val qualityCalculator: QualityCalculator = QualityCalculator(model)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DietQualityScoringTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    DietQualityScore(model, qualityCalculator)
                }
            }
        }
    }
}

@Composable
fun DietQualityScore(model: Meals, qualityCalculator: QualityCalculator) {
    var vegetables by remember {
        mutableStateOf(model.count)
    }

    var score by remember {
        mutableStateOf(0)
    }

    Column {
        Text("Diet Quality Score", style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.size(10.dp))
        Text("Today you had ${vegetables} meals")
        Spacer(modifier = Modifier.size(10.dp))
        Text("Your score is ${score}")
        OutlinedButton(onClick = {
            vegetables++
            score = qualityCalculator.calculateScore()
            model.count = vegetables
        }) {
            Text("Vegetables")
        }
    }
}