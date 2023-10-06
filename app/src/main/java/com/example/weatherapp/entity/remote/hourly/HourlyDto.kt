package com.example.weatherapp.entity.remote.hourly

import com.example.weatherapp.entity.remote.hourly.HourlyDataDto
import com.squareup.moshi.Json

data class HourlyDto(

    @field:Json(name = "hourly")
    val hourly: HourlyDataDto
)