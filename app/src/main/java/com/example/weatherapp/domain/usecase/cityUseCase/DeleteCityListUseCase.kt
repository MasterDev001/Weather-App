package com.example.weatherapp.domain.usecase.cityUseCase

import com.example.weatherapp.domain.model.CityData
import com.example.weatherapp.domain.toCityEntity
import com.example.weatherapp.entity.repository.CityRepository
import javax.inject.Inject

class DeleteCityListUseCase @Inject constructor(private val cityRepository: CityRepository) {

    suspend operator fun invoke(cityDataList: List<CityData>) =
        cityRepository.deleteCityList(cityDataList.map { it.toCityEntity() })
}