package com.example.weatherapp.domain.model

data class CityData(
    val key: String,
    val cityName: String,
    val country: String,
    val region: String,
    val latitude: Double,
    val longitude: Double,
    val conditionData: ConditionData?=null,
    val temperatureMin:Int?=null,
    val temperatureMax:Int?=null,
    val currentTemperature:Int?=null,
    val isCurrent:Boolean=false,
)
