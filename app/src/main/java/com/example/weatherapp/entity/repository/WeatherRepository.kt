package com.example.weatherapp.entity.repository

import com.example.weatherapp.entity.remote.weather.CityDto
import com.example.weatherapp.entity.remote.weather.WeatherDto

interface WeatherRepository {

    suspend fun getWeatherData(location:String,days:Int): WeatherDto
    suspend fun getCityList(location:String): List<CityDto>

}