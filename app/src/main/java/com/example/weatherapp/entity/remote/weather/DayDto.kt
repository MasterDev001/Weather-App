package com.example.weatherapp.entity.remote.weather


import com.squareup.moshi.Json

data class DayDto(

    @field:Json(name = "avgtemp_c")
    val avgTemp: Double,
    @field:Json(name = "mintemp_c")
    val minTemp: Double,
    @field:Json(name = "maxtemp_c")
    val maxTemp: Double,
    @field:Json(name = "totalprecip_mm")
    val totalPrecip: Double,
    @field:Json(name = "uv")
    val uv: Double,
    @field:Json(name = "avgvis_km")
    val avgVisibility: Double,
    @field:Json(name = "maxwind_kph")
    val maxWind: Double,
    @field:Json(name = "avghumidity")
    val avgHumidity: Double,
    @field:Json(name = "condition")
    val conditionDto: ConditionDto,
    @field:Json(name = "totalsnow_cm")
    val totalSnowCm: Double,

//    @field:Json(name = "avgtemp_f")
//    val avgtempF: Double,
//    @field:Json(name = "avgvis_miles")
//    val avgvisMiles: Double,
//    @field:Json(name = "daily_chance_of_rain")
//    val dailyChanceOfRain: Int,
//    @field:Json(name = "daily_chance_of_snow")
//    val dailyChanceOfSnow: Int,
//    @field:Json(name = "daily_will_it_rain")
//    val dailyWillItRain: Int,
//    @field:Json(name = "daily_will_it_snow")
//    val dailyWillItSnow: Int,
//    @field:Json(name = "maxtemp_f")
//    val maxtempF: Double,
//    @field:Json(name = "maxwind_mph")
//    val maxwindMph: Double,
//    @field:Json(name = "mintemp_f")
//    val mintempF: Double,
//    @field:Json(name = "totalprecip_in")
//    val totalprecipIn: Double,


)