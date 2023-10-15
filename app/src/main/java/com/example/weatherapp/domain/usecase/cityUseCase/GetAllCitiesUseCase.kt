package com.example.weatherapp.domain.usecase.cityUseCase

import android.annotation.SuppressLint
import android.net.http.HttpException
import com.example.weatherapp.common.ResultData
import com.example.weatherapp.domain.model.CityData
import com.example.weatherapp.domain.toCityData
import com.example.weatherapp.domain.toConditionData
import com.example.weatherapp.entity.repository.CityRepository
import com.example.weatherapp.entity.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject

class GetAllCitiesUseCase @Inject constructor(
    private val cityRepository: CityRepository,
    private val weatherRepository: WeatherRepository
) {

    @SuppressLint("NewApi")
    suspend operator fun invoke(): Flow<ResultData<List<CityData>>> = flow {
        emit(ResultData.Loading(true))
        val response = try {
            ResultData.Success(cityRepository.getAllCities()
                .map {
                    val weather = weatherRepository.getWeatherData(
                        "${it.latitude},${it.longitude}",
                        days = 1
                    )
                    it.toCityData().copy(
                        temperatureMax = weather.forecastDto.forecastDay.first().dayDto.maxTemp.toInt(),
                        temperatureMin = weather.forecastDto.forecastDay.first().dayDto.minTemp.toInt(),
                        conditionData = weather.currentDto.conditionDto.toConditionData(weather.currentDto.isDay == 1),
                        currentTemperature = weather.currentDto.temperature.toInt()
                    )
                }
            )
        } catch (e: IOException) {
            ResultData.Error(e.message.toString())
        }
        emit(response)
    }.flowOn(Dispatchers.IO)
}
