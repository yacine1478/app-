package com.example.myapplication.presentation.view.pages

import org.json.JSONException
import org.json.JSONObject
import java.net.URL

class WeatherService {

    private val apiKey = "2e104ce9c78536cbb4b197ffeb8c9f11" // Replace with your actual API key
    private val baseUrl = "https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={2e104ce9c78536cbb4b197ffeb8c9f11}"

    fun getCurrentWeather(location: String, callback: (WeatherModel?) -> Unit) {
        val url = "$baseUrl?q=$location&appid=$apiKey&units=metric"

        // Use a background thread to fetch the weather data
        Thread {
            try {
                val response = URL(url).readText()
                val weatherModel = parseWeatherData(response)
                callback(weatherModel)
                val temperature: Double
                val condition: String

            } catch (e: Exception) {
                e.printStackTrace()
                callback(null)
            }
        }.start()
    }
    private fun parseWeatherData(response: String): WeatherModel? {
        return try {
            val jsonObject = JSONObject(response)
            val main = jsonObject.getJSONObject("main")
            val weatherArray = jsonObject.getJSONArray("weather")
            val weather = weatherArray.getJSONObject(0)

            WeatherModel(
                temperature = main.getDouble("temp"),
                humidity = main.getInt("humidity"),
                condition = weather.getString("main"),
                description = weather.getString("description"),
                weatherCondition = weather.getString("main")
            )
        } catch (e: JSONException) {
            e.printStackTrace()
            null
        }
    }
}

