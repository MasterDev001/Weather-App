package com.example.weatherapp.entity.remote.weather


import com.squareup.moshi.Json

data class AstroDto(

    @field:Json(name = "moonrise")
    val moonRise: String,
    @field:Json(name = "moonset")
    val moonSet: String,
    @field:Json(name = "sunrise")
    val sunrise: String,
    @field:Json(name = "sunset")
    val sunset: String

//    @field:Json(name = "is_moon_up")
//    val isMoonUp: Int,
//    @field:Json(name = "is_sun_up")
//    val isSunUp: Int,
//    @field:Json(name = "moon_illumination")
//    val moonIllumination: String,
//    @field:Json(name = "moon_phase")
//    val moonPhase: String,
)