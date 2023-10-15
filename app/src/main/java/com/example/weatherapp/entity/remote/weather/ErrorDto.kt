package com.example.weatherapp.entity.remote.weather

import com.squareup.moshi.Json

data class ErrorDto(

@field:Json(name = "code")
val code: Int,
@field:Json(name = "message")
val message: String,

)
