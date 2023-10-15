package com.example.weatherapp.entity.remote.weather


import com.squareup.moshi.Json

data class LocationDto(
    @field:Json(name = "country")
    val country: String,
    @field:Json(name = "lat")
    val lat: Double,
    @field:Json(name = "lon")
    val lon: Double,
    @field:Json(name = "localtime")
    val localtime: String,
    @field:Json(name = "localtime_epoch")
    val localtimeEpoch: Int,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "region")
    val region: String,
    @field:Json(name = "tz_id")
    val tzId: String
)