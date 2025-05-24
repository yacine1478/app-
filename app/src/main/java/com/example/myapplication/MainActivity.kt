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
import androidx.compose.material3.ExperimentalMaterial3Api // لإصلاح تحذير @OptIn
import com.example.myapplication.presentation.view.pages.GroupsPanelsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigator()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class) // أضف هذا لتجنب تحذيرات Scaffold
@Composable
fun AppNavigator() {
    var currentScreen by remember { mutableStateOf("intro") }
    // استخدم هذه الحالة لتحديد الشاشة المختارة في شريط التنقل السفلي
    var selectedBottomNavItem by remember { mutableStateOf("home") }

    // نقل Scaffold مع BottomNavigationBar إلى مستوى أعلى ليغلف الشاشات التي تحتاجها
    Scaffold(
        bottomBar = {
            // أظهر الشريط السفلي فقط عندما يكون في الشاشات الرئيسية
            if (currentScreen == "home" || currentScreen == "add" || currentScreen == "profile" || currentScreen == "market" || currentScreen == "GroupsPanels" || currentScreen == "editProfile" ) {
                BottomNavigationBar(
                    selectedScreen = selectedBottomNavItem, // استخدم الحالة الجديدة هنا
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
                    // يمكنك إضافة المزيد من أزرار التنقل هنا وتحديث currentScreen و selectedBottomNavItem
                )
            }
        }
    ) { innerPadding ->
        // Box لتطبيق الـ padding الذي يوفره Scaffold
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding) // تطبيق المساحات الداخلية للحفاظ على المحتوى بعيدًا عن الـ bottomBar
        ) {
            when (currentScreen) {
                "intro" -> {
                    LaunchedEffect(Unit) {
                        delay(2000)
                        currentScreen = "login" // التغيير هنا: ينتقل إلى login
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
                            selectedBottomNavItem = "home" // بعد تسجيل الدخول بنجاح، انتقل إلى home واضبط العنصر المحدد
                        },
                        onSignInClick = {
                            currentScreen = "sign" // الانتقال إلى شاشة التسجيل
                        },
                        onGoogleLoginClick = { /* نفذ تسجيل دخول Google هنا */ }
                    )
                }
                "sign" -> {
                    sign(onSignUpSuccess = {
                        currentScreen = "login" // بعد التسجيل بنجاح، ارجع إلى شاشة تسجيل الدخول
                    })
                }
                "home" -> {
                    // لم يعد HomepageWithProfileNav يحتاج إلى Scaffold داخليًا
                    HomepageContent(
                        onProfileClick = {
                            currentScreen = "profile"
                            selectedBottomNavItem = "profile"
                        }
                    )
                }
                "profile" -> {
                    ProfilPreview(
                        onEditProfile = { currentScreen = "editProfile" },
                        onSolarGroups = { currentScreen = "GroupsPanels"},
                        onMarketShops = { currentScreen = "market" }
                    )
                }
                "editProfile" -> {
                    EditProfileScreen()
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

// تم فصل محتوى Homepage ليتناسب مع الهيكل الجديد
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