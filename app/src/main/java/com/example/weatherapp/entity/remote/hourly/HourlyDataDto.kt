package com.example.weatherapp.entity.remote.hourly

import com.squareup.moshi.Json


data class HourlyDataDto(
    @field:Json(name = "time")
    val hourList: List<String>,
    @field:Json(name = "is_day")
    val isDayList: List<Int>,
    @field:Json(name = "precipitation_probability")
    val precipitationList: List<Int>,
    @field:Json(name = "pressure_msl")
    val pressureList: List<Double>,
    @field:Json(name = "relativehumidity_2m")
    val humidityList: List<Int>,
    @field:Json(name = "temperature_2m")
    val temperatureList: List<Double>,
    @field:Json(name = "uv_index")
    val uvIndexList: List<Double>,
    @field:Json(name = "visibility")
    val visibilityList: List<Int>,
    @field:Json(name = "weathercode")
    val weatherCodeList: List<Int>,
    @field:Json(name = "winddirection_10m")
    val windDirectionList: List<Int>,
    @field:Json(name = "windspeed_10m")
    val windSpeedList: List<Double>
)