package com.example.weatherapp.domain.model

data class WeatherData(

    val locationData: LocationData,
    val currentData: CurrentData,
    val dailyData: List<DailyData>,
    val hourlyData: List<HourlyData>
)
