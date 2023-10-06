package com.example.weatherapp.domain.usecase.cityUseCase

import com.example.weatherapp.domain.toCityData
import com.example.weatherapp.entity.repository.CityRepository
import javax.inject.Inject

class GetCityFromLocalUseCase @Inject constructor(private val cityRepository: CityRepository) {

    suspend operator fun invoke(cityId: Int) = cityRepository.getCityFromLocal(cityId).toCityData()
}