package com.example.myapplication.presentation.view.pages

import androidx.compose.runtime.mutableStateListOf

data class SolarPanel(
    val id: Int,
    val status: String,
    val voltage: Double,
    val current: Double,
    val temperature: Double,
    val address: String
)

object SolarPanelRepository {
    val newPanels = mutableStateListOf<SolarPanel>()
}