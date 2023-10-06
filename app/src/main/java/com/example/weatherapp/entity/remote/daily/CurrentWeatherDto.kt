package com.example.weatherapp.entity.remote.daily

import com.squareup.moshi.Json

data class CurrentWeatherDto(
    @field:Json(name ="temperature")
    val currentTemperature:Double,
    @field:Json(name ="weathercode")
    val weatherCode:Int
)
