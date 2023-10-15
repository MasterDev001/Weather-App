package com.example.weatherapp.domain.model


data class LocationData(
    val country: String,
    val lat: Double,
    val lon: Double,
    val name: String,
    val region: String,
    val timezoneId:String
)