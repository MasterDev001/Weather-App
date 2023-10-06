package com.example.weatherapp.domain.repositoryImpl

import com.example.weatherapp.entity.local.CityDao
import com.example.weatherapp.entity.local.CityEntity
import com.example.weatherapp.entity.remote.CityApi
import com.example.weatherapp.entity.remote.geocoding.CityDto
import com.example.weatherapp.entity.repository.CityRepository
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    private val cityApi: CityApi,
    private val cityDao: CityDao
) : CityRepository {

    override suspend fun getCity(name: String, count: Int): CityDto {
        return cityApi.getCityData(name, count)
    }

    override suspend fun addCity(cityEntity: CityEntity): Long {
        cityDao.prepareToSelectCurrent()
        return cityDao.addCity(cityEntity)
    }

    override suspend fun deleteCity(id: Int): Int {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCityList(cityList: List<CityEntity>): Int {
        return cityDao.deleteCityList(cityList)
    }

    override suspend fun getCityFromLocal(id: Int): CityEntity {
        return cityDao.getCity(id)
    }

    override suspend fun getAllCities(): List<CityEntity> {
        return cityDao.getAllCites()
    }

    override suspend fun getCurrentCity(): CityEntity {
        return cityDao.getCurrentCity()
    }

}