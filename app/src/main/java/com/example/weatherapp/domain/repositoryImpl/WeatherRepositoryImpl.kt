package com.example.weatherapp.domain.repositoryImpl

import android.util.Log
import com.example.weatherapp.common.ResultData
import com.example.weatherapp.entity.remote.WeatherApi
import com.example.weatherapp.entity.remote.daily.DailyDto
import com.example.weatherapp.entity.remote.hourly.HourlyDto
import com.example.weatherapp.entity.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi
) : WeatherRepository {

    override suspend fun getHourlyData(lat: Double, long: Double): HourlyDto {
        return weatherApi.getHourlyData(lat,long)
    }

    override suspend fun getDailyData(lat: Double, long: Double): DailyDto {
        return weatherApi.getDailyData(lat,long)
    }

}