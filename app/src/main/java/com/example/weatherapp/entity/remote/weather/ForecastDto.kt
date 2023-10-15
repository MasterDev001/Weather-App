package com.example.weatherapp.entity.remote.weather


import com.squareup.moshi.Json


data class ForecastDto(

    @field:Json(name = "forecastday")
    val forecastDay: List<ForecastDay>
)