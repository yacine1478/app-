// presentation/view/Navigator/AppNavigator.kt
package com.example.myapplication.presentation.view.Navigator

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.presentation.Introscreen
import com.example.myapplication.presentation.utils.Routes

@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.INTRO_SCREEN
    ) {
        composable(Routes.INTRO_SCREEN) {
            Introscreen(
                onNavigateForward = {
                    // Example navigation:
                    // navController.navigate(Routes.HOME_SCREEN)
                }
            )
        }

        // Add other screens here:
        /*
        composable(Routes.HOME_SCREEN) {
            HomeScreen()
        }
        */
    }
}