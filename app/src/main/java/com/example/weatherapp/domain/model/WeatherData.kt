package com.example.weatherapp.domain.model

data class WeatherData(
    val hourlyData: List<HourlyData>,
    val dailyData: List<DailyData>
)
