package com.example.food_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.food_project.ui.MainScreen
import com.example.food_project.ui.theme.Food_projectTheme
import com.example.food_project.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Food_projectTheme {
                val mainViewModel: MainViewModel = viewModel()
                MainScreen(viewModel = mainViewModel)
            }
        }
    }
}
