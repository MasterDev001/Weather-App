package com.example.weatherapp.entity.remote

import com.example.weatherapp.entity.remote.daily.DailyDto
import com.example.weatherapp.entity.remote.hourly.HourlyDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("v1/forecast?hourly=temperature_2m,relativehumidity_2m,precipitation_probability,weathercode,pressure_msl,visibility,windspeed_10m,winddirection_10m,uv_index,is_day&timezone=auto")
    suspend fun getHourlyData(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double
    ): HourlyDto

    @GET("v1/forecast?daily=weathercode,temperature_2m_max,temperature_2m_min,sunrise,sunset,uv_index_max,precipitation_sum&timezone=auto&current_weather=true")
    suspend fun getDailyData(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double
    ): DailyDto

}