package com.example.weatherapp.domain.usecase.cityUseCase

import android.annotation.SuppressLint
import android.util.Log
import com.example.weatherapp.common.ResultData
import com.example.weatherapp.domain.model.CityData
import com.example.weatherapp.domain.toCityData
import com.example.weatherapp.domain.toListDailyData
import com.example.weatherapp.domain.toListHourlyData
import com.example.weatherapp.entity.local.CityEntity
import com.example.weatherapp.entity.repository.CityRepository
import com.example.weatherapp.entity.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.cancel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime
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
                    val dailyData = weatherRepository.getDailyData(
                        it.latitude,
                        it.longitude
                    ).toListDailyData().first()
                    it.toCityData().copy(
                        temperatureMax = dailyData.temperatureMax,
                        temperatureMin = dailyData.temperatureMin,
                        weatherType = dailyData.currentWeatherType,
                        currentTemperature = dailyData.currentTemperature
                    )
                }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            ResultData.Error(e.message.toString())
        }
        emit(response)
    }.flowOn(Dispatchers.IO)
}
