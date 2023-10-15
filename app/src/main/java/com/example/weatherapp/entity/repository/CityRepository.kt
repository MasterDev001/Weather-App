package com.example.weatherapp.entity.repository

import com.example.weatherapp.entity.local.CityEntity

interface CityRepository {

    suspend fun addCity(cityEntity: CityEntity):Long
     fun deleteCity(key:String):Int
    suspend fun getCityFromLocal(key:String):CityEntity
    suspend fun getAllCities():List<CityEntity>
    suspend fun getCurrentCity():CityEntity?
    suspend fun selectCurrentCity(key: String):Int
}