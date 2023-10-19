package com.example.weatherapp.entity.remote.weather


import com.squareup.moshi.Json

data class CityDto(

    @field:Json(name = "country")
    val country: String,
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "lat")
    val lat: Double,
    @field:Json(name = "lon")
    val lon: Double,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "region")
    val region: String,
    @field:Json(name = "url")
    val url: String
)