package com.example.weatherapp.entity.repository

import com.example.weatherapp.common.ResultData
import com.example.weatherapp.entity.local.CityEntity
import com.example.weatherapp.entity.remote.geocoding.CityDto
import kotlinx.coroutines.flow.Flow

interface CityRepository {

    suspend fun getCity(name:String,count:Int):CityDto

    suspend fun addCity(cityEntity: CityEntity):Long
    suspend fun deleteCity(id:Int):Int
    suspend fun deleteCityList(cityList: List<CityEntity>):Int
    suspend fun getCityFromLocal(id:Int):CityEntity
    suspend fun getAllCities():List<CityEntity>
    suspend fun getCurrentCity():CityEntity
}