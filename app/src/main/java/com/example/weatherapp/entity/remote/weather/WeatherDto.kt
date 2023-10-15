package com.example.weatherapp.entity.remote.weather


import com.squareup.moshi.Json

data class WeatherDto(

    @field:Json(name = "current")
    val currentDto: CurrentDto,
    @field:Json(name = "forecast")
    val forecastDto: ForecastDto,
    @field:Json(name = "location")
    val locationDto: LocationDto,
    @field:Json(name = "error")
    val errorDto: ErrorDto?
)