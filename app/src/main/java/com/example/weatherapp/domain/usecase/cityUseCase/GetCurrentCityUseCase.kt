package com.example.weatherapp.domain.usecase.cityUseCase

import com.example.weatherapp.domain.model.CityData
import com.example.weatherapp.domain.toCityData
import com.example.weatherapp.entity.repository.CityRepository
import javax.inject.Inject

class GetCurrentCityUseCase @Inject constructor(private val cityRepository: CityRepository) {

    suspend operator fun invoke(): CityData? = cityRepository.getCurrentCity()?.toCityData()
}