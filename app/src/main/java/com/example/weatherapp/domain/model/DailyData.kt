package com.example.weatherapp.domain.model

import java.time.LocalDateTime

data class DailyData(
    val date: LocalDateTime,
    val sunRise: String,
    val sunSet: String,
    val avgTemperature: Int,
    val temperatureMax: Int,
    val temperatureMin: Int,
    val totalPrecipitation: Double,
    val visibility: Double,
    val maxWindSpeed: Double,
    val humidity: Double,
    val uv: Double,
    val conditionData: ConditionData,
    val snow:Double
)
