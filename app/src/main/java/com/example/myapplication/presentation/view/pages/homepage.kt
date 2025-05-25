package com.example.myapplication.presentation.view.pages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import com.example.myapplication.presentation.view.pages.PreviewFakeHomeScreen
class HomepageActivity : ComponentActivity() {
    private lateinit var weatherService: WeatherService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        weatherService = WeatherService()

        setContent {
            Homepage(weatherService)
        }
    }
}


@Composable
fun Homepage(weatherService: WeatherService) {
    var currentScreen by remember { mutableStateOf("home") }
    var weatherData by remember { mutableStateOf<WeatherModel?>(null) }
    var loading by remember { mutableStateOf(true) }

    when (currentScreen) {
        "home" -> {
            LaunchedEffect(Unit) {
                loading = true
                weatherService.getCurrentWeather("your_location") { result ->
                    weatherData = result
                    loading = false
                }
            }
            Scaffold(
                bottomBar = {
                    BottomNavigationBar(
                        selectedScreen = currentScreen,
                        onHomeClick = { currentScreen = "home" },
                        onAddClick = { currentScreen = "add" },
                        onProfileClick = { /* يمكنك إضافة شاشة البروفايل لاحقًا */ }
                    )
                }
            ) { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    if (loading) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    } else {
                        weatherData?.let {
                            WeatherDisplay(it)
                        } ?: run {
                            Text(
                                "Error fetching weather data",
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
            }
        }
        "add" -> {
            Scaffold(
                bottomBar = {
                    BottomNavigationBar(
                        selectedScreen = currentScreen,
                        onHomeClick = { currentScreen = "home" },
                        onAddClick = { /* بالفعل هنا */ },
                        onProfileClick = { /* يمكنك إضافة شاشة البروفايل لاحقًا */ }
                    )
                }
            ) { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    AddSolarPanelScreen()
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    selectedScreen: String,
    onHomeClick: () -> Unit,
    onAddClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 0.dp
    ) {
        // Home
        NavigationBarItem(
            icon = {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Color.Black)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = "Home",
                        tint = Color.White, // دائماً أبيض
                        modifier = Modifier.size(20.dp)
                    )
                }
            },
            selected = selectedScreen == "home",
            onClick = onHomeClick,
            alwaysShowLabel = false
        )
        // Add
        NavigationBarItem(
            icon = {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Color.Black)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Add",
                        tint = if (selectedScreen == "add") Color.Black else Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            },
            selected = selectedScreen == "add",
            onClick = onAddClick,
            alwaysShowLabel = false
        )
        // Profile
        NavigationBarItem(
            icon = {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Color.Black)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Profile",
                        tint = if (selectedScreen == "profile") Color.Black else Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            },
            selected = selectedScreen == "profile",
            onClick = onProfileClick,
            alwaysShowLabel = false
        )
    }
}
// مثال لعرض بيانات الطقس
@Composable
fun WeatherDisplay(weather: WeatherModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Temperature: ${weather.temperature}°C")
        Text(text = "Humidity: ${weather.humidity}%")
        Text(text = "Condition: ${weather.condition}")
    }
}

@Preview(showBackground = true)
@Composable
fun Homepage() {
    Homepage(WeatherService())

}