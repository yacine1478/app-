package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import com.example.myapplication.presentation.view.pages.intropage
import com.example.myapplication.presentation.view.pages.sign
import com.example.myapplication.presentation.view.pages.WeatherService
import com.example.myapplication.presentation.view.pages.login
import com.example.myapplication.presentation.view.pages.EditProfileScreen
import com.example.myapplication.presentation.view.pages.MarketShope
import com.example.myapplication.presentation.view.pages.ProfilPreview
import com.example.myapplication.presentation.view.pages.WeatherDisplay
import com.example.myapplication.presentation.view.pages.WeatherModel
import com.example.myapplication.presentation.view.pages.AddSolarPanelScreen
import com.example.myapplication.presentation.view.pages.BottomNavigationBar
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.myapplication.presentation.view.pages.GroupsPanelsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigator()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigator() {
    var currentScreen by remember { mutableStateOf("intro") }
    var selectedBottomNavItem by remember { mutableStateOf("home") }
    var currentUsername by remember { mutableStateOf("YACINE AHMID") }


    Scaffold(
        bottomBar = {

            if (currentScreen == "home" || currentScreen == "add" || currentScreen == "profile" || currentScreen == "market" || currentScreen == "GroupsPanels" || currentScreen == "editProfile" ) {
                BottomNavigationBar(
                    selectedScreen = selectedBottomNavItem,
                    onHomeClick = {
                        currentScreen = "home"
                        selectedBottomNavItem = "home"
                    },
                    onAddClick = {
                        currentScreen = "add"
                        selectedBottomNavItem = "add"
                    },
                    onProfileClick = {
                        currentScreen = "profile"
                        selectedBottomNavItem = "profile"
                    }

                )
            }
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (currentScreen) {
                "intro" -> {
                    LaunchedEffect(Unit) {
                        delay(2000)
                        currentScreen = "login"
                    }
                    intropage(
                        title = "SUNSIGHT",
                        subtitle = "WELCOME TO NEW FUTURE"
                    )
                }
                "login" -> {
                    login(
                        onLoginSuccess = {
                            currentScreen = "home"
                            selectedBottomNavItem = "home"
                        },
                        onSignInClick = {
                            currentScreen = "sign"
                        },
                        onGoogleLoginClick = {}
                    )
                }
                "sign" -> {
                    sign(onSignUpSuccess = {
                        currentScreen = "login"
                    })
                }
                "home" -> {

                    HomepageContent(
                        onProfileClick = {
                            currentScreen = "profile"
                            selectedBottomNavItem = "profile"
                        }
                    )
                }
                "profile" -> {
                    ProfilPreview(
                        username = currentUsername,
                        onEditProfile = { currentScreen = "editProfile" },
                        onSolarGroups = { currentScreen = "GroupsPanels"},
                        onMarketShops = { currentScreen = "market" }
                    )
                }
                "editProfile" -> {
                    EditProfileScreen(
                        currentUsername = currentUsername,
                        onSaveProfile = { newUsername ->
                            currentUsername = newUsername
                            currentScreen = "profile"
                        }
                    )
                }
                "market" -> {
                    MarketShope()
                }
                "add" -> {
                    AddSolarPanelScreen()
                }
                "GroupsPanels" -> {
                    GroupsPanelsScreen()
                }
            }
        }
    }
}


@Composable
fun HomepageContent(onProfileClick: () -> Unit) {
    val weatherService = remember { WeatherService() }
    var weatherData by remember { mutableStateOf<WeatherModel?>(null) }
    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        loading = true
        weatherService.getCurrentWeather("your_location") { result ->
            weatherData = result
            loading = false
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
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
