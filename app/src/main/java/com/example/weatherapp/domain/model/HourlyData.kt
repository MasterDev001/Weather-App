package com.example.weatherapp.domain.model

data class HourlyData(
    val hour: String,
    val precipitation: Int,
    val pressure: Double,
    val humidity: Int,
    val temperature: Int,
    val visibility: Int,
    val windSpeed: Double,
    val uvIndex: Double,
    val windDirection: Int,
    val weatherType: WeatherType,
    val isDay: Boolean
)

