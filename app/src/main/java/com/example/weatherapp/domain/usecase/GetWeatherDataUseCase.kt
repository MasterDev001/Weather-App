package com.example.weatherapp.domain.usecase

import android.util.Log
import com.example.weatherapp.common.ResultData
import com.example.weatherapp.domain.model.WeatherData
import com.example.weatherapp.domain.toWeatherData
import com.example.weatherapp.entity.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject

class GetWeatherDataUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {

    suspend operator fun invoke(location:String) = flow<ResultData<WeatherData>> {

        emit(ResultData.Loading(isLoading = true))
        val response = try {
            ResultData.Success(
              weatherRepository.getWeatherData(location, days = 7).toWeatherData()
            )
        } catch (e: Exception) {
            ResultData.Error(e.message.toString())
        }
        emit(response)
    }.flowOn(Dispatchers.IO)
}

