package com.example.weatherapp.entity.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cityTable")
data class  CityEntity(
    @PrimaryKey
    val id:Int,
    val cityName: String,
    val country: String,
    val admin1: String,
    val countryCode: String,
    val latitude: Double,
    val longitude: Double,
    val isCurrent:Boolean=false
)
