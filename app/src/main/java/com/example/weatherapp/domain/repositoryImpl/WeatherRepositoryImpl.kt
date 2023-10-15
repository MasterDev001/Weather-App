package com.example.weatherapp.domain.repositoryImpl

import com.example.weatherapp.entity.remote.WeatherApi
import com.example.weatherapp.entity.remote.weather.WeatherDto
import com.example.weatherapp.entity.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi
) : WeatherRepository {

    override suspend fun getWeatherData(location: String,days:Int): WeatherDto {
        return weatherApi.getWeatherData(location = location, days = days)
    }

}