package com.example.weatherapp.entity.remote.geocoding


import com.squareup.moshi.Json

data class CityDataDto(

    @field:Json(name = "admin1")
    val admin1: String,
    @field:Json(name = "admin1_id")
    val admin1Id: Int,
    @field:Json(name = "country")
    val country: String,
    @field:Json(name = "country_code")
    val countryCode: String,
    @field:Json(name = "country_id")
    val countryId: Int,
    @field:Json(name = "elevation")
    val elevation: Int,
    @field:Json(name = "feature_code")
    val featureCode: String,
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "latitude")
    val latitude: Double,
    @field:Json(name = "longitude")
    val longitude: Double,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "population")
    val population: Int,
    @field:Json(name = "timezone")
    val timezone: String
)