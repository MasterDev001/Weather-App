package com.example.weatherapp.app.di

import com.example.weatherapp.domain.repositoryImpl.CityRepositoryImpl
import com.example.weatherapp.domain.repositoryImpl.WeatherRepositoryImpl
import com.example.weatherapp.entity.local.CityDao
import com.example.weatherapp.entity.remote.CityApi
import com.example.weatherapp.entity.remote.WeatherApi
import com.example.weatherapp.entity.repository.CityRepository
import com.example.weatherapp.entity.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @[Provides Singleton]
    fun provideWeatherRepository(weatherApi: WeatherApi): WeatherRepository =
        WeatherRepositoryImpl(weatherApi)

    @[Provides Singleton]
    fun provideCityRepository(cityApi: CityApi, cityDao: CityDao): CityRepository =
        CityRepositoryImpl(cityApi, cityDao)
}