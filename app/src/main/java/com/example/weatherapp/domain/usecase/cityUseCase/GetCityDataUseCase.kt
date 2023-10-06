package com.example.weatherapp.domain.usecase.cityUseCase

import com.example.weatherapp.common.ResultData
import com.example.weatherapp.domain.model.CityData
import com.example.weatherapp.domain.toCityDataList
import com.example.weatherapp.entity.repository.CityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetCityDataUseCase @Inject constructor(private val cityRepository: CityRepository) {

    suspend operator fun invoke(name: String, count: Int = 1) =
        cityRepository.getCity(name, count).toCityDataList()

}