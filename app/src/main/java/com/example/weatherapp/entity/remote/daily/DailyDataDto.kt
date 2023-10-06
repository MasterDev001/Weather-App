package com.example.weatherapp.entity.remote.daily


import com.squareup.moshi.Json

data class DailyDataDto(

    @field:Json(name ="time")
    val dateList: List<String>,
    @field:Json(name ="precipitation_sum")
    val precipitationSumList: List<Double>,
    @field:Json(name ="sunrise")
    val sunriseList: List<String>,
    @field:Json(name ="sunset")
    val sunsetList: List<String>,
    @field:Json(name ="temperature_2m_max")
    val temperatureMaxList: List<Double>,
    @field:Json(name ="temperature_2m_min")
    val temperatureMinList: List<Double>,
    @field:Json(name ="uv_index_max")
    val uvIndexMaxList: List<Double>,
    @field:Json(name ="weathercode")
    val weatherCodeList: List<Int>
)