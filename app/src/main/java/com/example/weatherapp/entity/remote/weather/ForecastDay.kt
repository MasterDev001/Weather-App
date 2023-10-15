package com.example.weatherapp.entity.remote.weather


import com.squareup.moshi.Json


data class ForecastDay(

    @field:Json(name = "astro")
    val astroDto: AstroDto,
    @field:Json(name = "date")
    val date: String,
    @field:Json(name = "date_epoch")
    val dateEpoch: Int,
    @field:Json(name = "day")
    val dayDto: DayDto,
    @field:Json(name = "hour")
    val hourDto: List<HourDto>
)