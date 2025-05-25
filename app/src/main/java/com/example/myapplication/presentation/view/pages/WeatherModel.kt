package com.example.myapplication.presentation.view.pages



data class WeatherModel(
    val temperature: Double,
    val humidity: Int,
    val condition: String,
    val description: String,
    val weatherCondition: String
) {
    fun getFormattedTemperature(): String {
        return "${temperature.toInt()}°C"
    }

    fun getFormattedHumidity(): String {
        return "$humidity%"
    }

    fun getWeatherDescription(): String {
        return weatherCondition.replaceFirstChar { it.uppercase() }
    }
}