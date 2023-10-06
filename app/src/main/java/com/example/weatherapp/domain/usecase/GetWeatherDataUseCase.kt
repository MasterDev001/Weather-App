package com.example.weatherapp.domain.usecase

import com.example.weatherapp.common.ResultData
import com.example.weatherapp.domain.model.WeatherData
import com.example.weatherapp.domain.toListDailyData
import com.example.weatherapp.domain.toListHourlyData
import com.example.weatherapp.entity.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject

class GetWeatherDataUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {

    suspend operator fun invoke(lat: Double, long: Double) = flow<ResultData<WeatherData>> {

        emit(ResultData.Loading(isLoading = true))
        val response = try {
            ResultData.Success(
                WeatherData(
                    hourlyData =
                    weatherRepository.getHourlyData(lat, long).hourly.toListHourlyData(),
                    dailyData = weatherRepository.getDailyData(lat, long).toListDailyData()
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
            ResultData.Error(e.message.toString())
        }
        emit(response)
    }.flowOn(Dispatchers.IO)
}
