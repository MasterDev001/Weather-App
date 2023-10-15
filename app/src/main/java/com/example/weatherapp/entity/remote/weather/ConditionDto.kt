package com.example.weatherapp.entity.remote.weather


import com.squareup.moshi.Json

data class ConditionDto(

    @field:Json(name = "code")
    val code: Int,
    @field:Json(name = "icon")
    val icon: String,
    @field:Json(name = "text")
    val text: String
)