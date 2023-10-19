package com.example.weatherapp.domain.usecase.cityUseCase

import com.example.weatherapp.common.ResultData
import com.example.weatherapp.domain.cityDtoToCityData
import com.example.weatherapp.domain.model.CityData
import com.example.weatherapp.domain.toCityData
import com.example.weatherapp.entity.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import javax.inject.Inject

class GetCityListUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {

    suspend operator fun invoke(cityName: String) = flow<ResultData<List<CityData>>> {
        emit(ResultData.Loading(isLoading = true))
        val response = try {
            ResultData.Success(weatherRepository.getCityList(cityName).map { it.cityDtoToCityData() })
        } catch (e: HttpException) {
            ResultData.Error("No matching location found.")
        }
        emit(response)
    }.flowOn(Dispatchers.IO)
}
