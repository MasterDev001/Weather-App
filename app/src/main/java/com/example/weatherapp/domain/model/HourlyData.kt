package com.example.weatherapp.domain.model

import java.time.LocalDateTime

data class HourlyData(
    val hour: String,
    val precipitation: Double,
    val pressure: Int,
    val humidity: Int,
    val temperature: Int,
    val visibility: Int,
    val windSpeed: Int,
    val uvIndex: Int,
    val feelsLike: Int,
    val conditionData: ConditionData,
    val isDay: Boolean,
)

