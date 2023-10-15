package com.example.weatherapp.domain.usecase.cityUseCase

import com.example.weatherapp.common.ResultData
import com.example.weatherapp.domain.model.CityData
import com.example.weatherapp.entity.repository.CityRepository
import com.example.weatherapp.presentation.view_models.location.LocationContract
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteCityUseCase @Inject constructor(
    private val cityRepository: CityRepository,
    private val selectCurrentCityUseCase: SelectCurrentCityUseCase
) {

    operator fun invoke(cityData: CityData, index: Int, cityList: List<CityData>) = flow {
        emit(ResultData.Loading(isLoading = true))
        if (cityData.isCurrent) {
            val citySize = cityList.size - 1
            val newIndex = if (index < citySize) index + 1 else index - 1
            val newCurrent = cityList[newIndex]
            selectCurrentCityUseCase.invoke(newCurrent.key).collect { result ->
                val response = when (result) {
                    is ResultData.Success -> {
                        ResultData.Success(data = cityRepository.deleteCity(cityData.key))
                    }

                    is ResultData.Error -> {
                        ResultData.Error(message = result.message)
                    }

                    is ResultData.Loading -> {
                        ResultData.Loading(isLoading = result.isLoading)
                    }
                }
                emit(response)
            }
        } else {
            emit(ResultData.Success(data = cityRepository.deleteCity(cityData.key)))
        }
    }
}