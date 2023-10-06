package com.example.weatherapp.domain.model

import java.time.LocalDateTime

data class DailyData(
    val date: LocalDateTime,
    val sunRise: String,
    val sunSet: String,
    val temperatureMax: Int,
    val temperatureMin: Int,
    val weatherType: WeatherType,
    val currentTemperature: Int,
    val currentWeatherType: WeatherType
)
