package com.example.weatherapp.domain.model

data class CityData(
    val id: Int,
    val cityName: String,
    val country: String,
    val admin1: String,
    val countryCode: String,
    val latitude: Double,
    val longitude: Double,
    val weatherType: WeatherType?=null,
    val temperatureMin:Int?=null,
    val temperatureMax:Int?=null,
    val currentTemperature:Int?=null,
    val isCurrent:Boolean=false,
)
