package com.example.myapplication.presentation.view.Navigator

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavController
import androidx.navigation.compose.composable
import androidx.navigation.findNavController
import com.example.myapplication.presentation.Introscreen


@Composable
fun AppNavigator() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "intro") {
    composable("intro"){ Introscreen (navController) }

    }
}