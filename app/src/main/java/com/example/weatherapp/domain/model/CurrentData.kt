package com.example.weatherapp.domain.model

data class CurrentData(
    val condition: ConditionData,
    val feelsLikeC: Double,
    val humidity: Int,
    val isDay: Boolean,
    val precipitation: Double,
    val pressure: Int,
    val temperature: Int,
    val uv: Int,
    val visibility: Int,
    val windSpeed: Int,
)
