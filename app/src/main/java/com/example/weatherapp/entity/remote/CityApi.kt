package com.example.weatherapp.entity.remote

import com.example.weatherapp.entity.remote.geocoding.CityDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CityApi {

    @GET("v1/search?language=en&format=json")
    suspend fun getCityData(
        @Query("name") cityName: String,
        @Query("count") count: Int = 1
    ): CityDto
}