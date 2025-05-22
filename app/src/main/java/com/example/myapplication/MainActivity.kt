// MainActivity.kt

package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import kotlinx.coroutines.delay
import com.example.myapplication.presentation.view.pages.intropage
import com.example.myapplication.presentation.view.pages.login
import com.example.myapplication.presentation.view.pages.sign
import com.example.myapplication.presentation.view.pages.Homepage
import com.example.myapplication.presentation.view.pages.WeatherService

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigator()
        }
    }
}

@Composable
fun AppNavigator() {
    var currentScreen by remember { mutableStateOf("intro") }

    when (currentScreen) {
        "intro" -> {
            LaunchedEffect(Unit) {
                delay(2000)
                currentScreen = "sign"
            }
            intropage(
                title = "SUNSIGHT",
                subtitle = "WELCOME TO NEW FUTURE"
            )
        }
        "sign" -> {
            sign(onSignUpSuccess = { currentScreen = "login" })
        }
        "login" -> {
            login(onLoginSuccess = { currentScreen = "home" })
        }
        "home" -> {
            Homepage(WeatherService())
        }
    }
}