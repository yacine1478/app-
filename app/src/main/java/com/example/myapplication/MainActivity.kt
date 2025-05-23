// MainActivity.kt
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
import com.example.myapplication.presentation.view.pages.Homepage
import com.example.myapplication.presentation.view.pages.WeatherService
import com.example.myapplication.presentation.view.pages.login
import com.example.myapplication.presentation.view.pages.EditProfileScreen
import com.example.myapplication.presentation.view.pages.MarketShope
import com.example.myapplication.presentation.view.pages.ProfilPreview
import com.example.myapplication.presentation.view.pages.WeatherDisplay
import com.example.myapplication.presentation.view.pages.WeatherModel
import com.example.myapplication.presentation.view.pages.AddSolarPanelScreen
import com.example.myapplication.presentation.view.pages.BottomNavigationBar

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
            login(
                onLoginSuccess = { currentScreen = "home" },
                onSignInClick = { currentScreen = "sign" }, // أو أي منطق آخر
                onGoogleLoginClick = { /* نفذ تسجيل دخول Google هنا */ }
            )
        }
        "home" -> {
            HomepageWithProfileNav(
                onProfileClick = { currentScreen = "profile" }
            )
        }
        "profile" -> {
            ProfilPreview(
                onEditProfile = { currentScreen = "editProfile" },
                onSolarGroups = { /* يمكنك إضافة شاشة المجموعات لاحقًا */ },
                onMarketShops = { currentScreen = "market" }
            )
        }
        "editProfile" -> {
            EditProfileScreen()
        }
        "market" -> {
            MarketShope()
        }
    }
}
@Composable
fun HomepageWithProfileNav(onProfileClick: () -> Unit) {
    val weatherService = remember { WeatherService() }
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
                        onProfileClick = onProfileClick
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
                        onProfileClick = onProfileClick
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