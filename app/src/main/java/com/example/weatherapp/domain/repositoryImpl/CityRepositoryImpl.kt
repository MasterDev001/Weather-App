package com.example.weatherapp.domain.repositoryImpl

import com.example.weatherapp.entity.local.CityDao
import com.example.weatherapp.entity.local.CityEntity
import com.example.weatherapp.entity.repository.CityRepository
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    private val cityDao: CityDao
) : CityRepository {


    override suspend fun addCity(cityEntity: CityEntity): Long {
        cityDao.prepareToSelectCurrent()
        return cityDao.addCity(cityEntity)
    }

    override  fun deleteCity(key: String): Int {
        return cityDao.deleteCity(key)
    }

    override suspend fun getCityFromLocal(key:String): CityEntity {
        return cityDao.getCity(key)
    }

    override suspend fun getAllCities(): List<CityEntity> {
        return cityDao.getAllCites()
    }

    override suspend fun getCurrentCity(): CityEntity? {
        return cityDao.getCurrentCity()
    }

    override suspend fun selectCurrentCity(key: String): Int {
        cityDao.prepareToSelectCurrent()
        return cityDao.selectCurrentCity(key)
    }

}