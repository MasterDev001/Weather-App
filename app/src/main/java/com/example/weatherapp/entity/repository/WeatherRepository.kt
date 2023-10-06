package com.example.weatherapp.entity.repository

import com.example.weatherapp.common.ResultData
import com.example.weatherapp.domain.model.HourlyData
import com.example.weatherapp.entity.remote.daily.DailyDto
import com.example.weatherapp.entity.remote.hourly.HourlyDataDto
import com.example.weatherapp.entity.remote.hourly.HourlyDto
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun getHourlyData(lat:Double,long:Double): HourlyDto
    suspend fun getDailyData(lat:Double,long:Double):DailyDto
}