package com.example.weatherapp.domain.usecase.cityUseCase

import com.example.weatherapp.common.ResultData
import com.example.weatherapp.entity.local.CityEntity
import com.example.weatherapp.entity.repository.CityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SelectCurrentCityUseCase @Inject constructor(private val cityRepository: CityRepository) {

    suspend operator fun invoke(cityKey: String) = flow {
        emit(ResultData.Loading(isLoading = true))
        val response = try {
            ResultData.Success(cityRepository.selectCurrentCity(cityKey))
        } catch (e: Exception) {
            e.printStackTrace()
            ResultData.Error(e.message.toString())
        }
        emit(response)
    }.flowOn(Dispatchers.IO)
}