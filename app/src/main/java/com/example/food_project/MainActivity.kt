package com.example.food_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.food_project.ui.theme.Food_projectTheme
import kotlinx.coroutines.delay
import com.example.food_project.ui.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Food_projectTheme {
                var showSplash by remember { mutableStateOf(true) }

                LaunchedEffect(Unit) {
                    delay(3000)
                    showSplash = false
                }

                if (showSplash) {
                    SplashScreen(
                    )
                } else {
                    MainScreen()
                }
            }

        }
    }
}

@Composable
fun SplashScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .aspectRatio(1f)
            )
            Text(
                text = "Loading recipes...",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineMedium
                )
        }
    }
}
