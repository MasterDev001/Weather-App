package com.example.weatherapp.entity.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cityTable")
data class  CityEntity(
    @PrimaryKey
    val key:String,
    val cityName: String,
    val country: String,
    val region: String,
    val latitude: Double,
    val longitude: Double,
    val isCurrent:Boolean=false
)
