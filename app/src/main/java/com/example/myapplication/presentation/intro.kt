// presentation/Introscreen.kt
package com.example.myapplication.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.presentation.view.pages.intropage
import kotlinx.coroutines.delay

@Composable
fun Introscreen(
    onNavigateForward: () -> Unit
) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(300) // Initial delay before animation starts
        visible = true
        delay(2000) // Delay before auto-navigation
        onNavigateForward()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF222222)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(animationSpec = tween(1000)) +
                    slideInVertically(animationSpec = tween(1000))
        ) {
            intropage(
                title = "SUNSIGHT",
                subtitle = "WELCOME TO NEW FUTURE"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun IntroPreview() {
    Introscreen(onNavigateForward = {})
}