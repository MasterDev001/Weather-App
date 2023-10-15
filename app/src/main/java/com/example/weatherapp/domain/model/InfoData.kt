package com.example.weatherapp.domain.model

data class InfoData(
    val condition: ConditionData,
    val humidity: Int,
    val precipitation: Double,
    val pressure: Int?=null,
    val temperature: Int,
    val uv: Int,
    val visibility: Int,
    val windSpeed: Int,
    val sunRise: String? = null,
    val sunSet: String? = null,
    val isDay: Boolean?=null,
    val snow: Double? = null,
)
