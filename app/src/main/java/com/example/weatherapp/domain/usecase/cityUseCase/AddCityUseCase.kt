package com.example.weatherapp.domain.usecase.cityUseCase

import android.util.Log
import com.example.weatherapp.common.ResultData
import com.example.weatherapp.entity.local.CityEntity
import com.example.weatherapp.entity.repository.CityRepository
import com.example.weatherapp.entity.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import javax.inject.Inject

class AddCityUseCase @Inject constructor(
    private val cityRepository: CityRepository,
    private val weatherRepository: WeatherRepository
) {

    suspend operator fun invoke(cityName: String) = flow {
        emit(ResultData.Loading(isLoading = true))
        val response = try {
            val weather = weatherRepository.getWeatherData(cityName, days = 1)
            ResultData.Success(
                cityRepository.addCity(
                    CityEntity(
                        key = "${weather.locationDto.name}${weather.locationDto.region}${weather.locationDto.country}",
                        cityName = weather.locationDto.name,
                        country = weather.locationDto.country,
                        region = weather.locationDto.region,
                        latitude = weather.locationDto.lat,
                        longitude = weather.locationDto.lon,
                        isCurrent = true
                    )
                )
            )
        } catch (e: HttpException) {
            ResultData.Error("No matching location found.")
        }
        emit(response)
    }.flowOn(Dispatchers.IO)
}
