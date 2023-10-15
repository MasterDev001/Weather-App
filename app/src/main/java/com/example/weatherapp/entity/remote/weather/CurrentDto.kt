package com.example.weatherapp.entity.remote.weather


import com.squareup.moshi.Json

data class CurrentDto(

    @field:Json(name = "condition")
    val conditionDto: ConditionDto,
    @field:Json(name = "feelslike_c")
    val feelsLikeC: Double,
    @field:Json(name = "humidity")
    val humidity: Int,
    @field:Json(name = "is_day")
    val isDay: Int,
    @field:Json(name = "precip_mm")
    val precipitation: Double,
    @field:Json(name = "pressure_mb")
    val pressure: Double,
    @field:Json(name = "temp_c")
    val temperature: Double,
    @field:Json(name = "uv")
    val uv: Double,
    @field:Json(name = "vis_km")
    val visibility: Double,
    @field:Json(name = "wind_kph")
    val windSpeed: Double,

    //    @field:Json(name = "last_updated")
//    val lastUpdated: String,
//    @field:Json(name = "last_updated_epoch")
//    val lastUpdatedEpoch: Int,
//    @field:Json(name = "cloud")
//    val cloud: Int,
//    @field:Json(name = "feelslike_f")
//    val feelslikeF: Double,
//    @field:Json(name = "gust_kph")
//    val gustKph: Double,
//    @field:Json(name = "gust_mph")
//    val gustMph: Double,
//    @field:Json(name = "precip_in")
//    val precipIn: Double,
//    @field:Json(name = "pressure_in")
//    val pressureIn: Double,
//    @field:Json(name = "temp_f")
//    val tempF: Double,
//    @field:Json(name = "vis_miles")
//    val visMiles: Double,
//    @field:Json(name = "wind_degree")
//    val windDegree: Int,
//    @field:Json(name = "wind_dir")
//    val windDir: String,
//    @field:Json(name = "wind_mph")
//    val windMph: Double
)