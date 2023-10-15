package com.example.weatherapp.entity.remote

import com.example.weatherapp.entity.remote.weather.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("v1/forecast.json?aqi=no&alerts=no&key=afcc84f4bb2e44e8b2c145704230610")
    suspend fun getWeatherData(
        @Query("q") location: String,
        @Query("days") days: Int
    ): WeatherDto


}