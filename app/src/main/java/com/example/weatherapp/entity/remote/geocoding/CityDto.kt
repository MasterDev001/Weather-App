package com.example.weatherapp.entity.remote.geocoding


import com.squareup.moshi.Json

data class CityDto(

    @field:Json(name = "results")
    val cityList: List<CityDataDto>
)