package com.example.weatherapp.entity.remote.daily

import com.squareup.moshi.Json

data class DailyDto(

    @field:Json(name = "current_weather")
    val currentWeather: CurrentWeatherDto,
    @field:Json(name = "daily")
    val daily: DailyDataDto
)
