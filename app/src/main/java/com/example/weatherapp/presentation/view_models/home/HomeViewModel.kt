package com.example.weatherapp.presentation.view_models.home

import kotlinx.coroutines.flow.StateFlow

interface HomeViewModel {

    val weatherState: StateFlow<HomeContract.WeatherState>

    fun reduce(action: (HomeContract.WeatherState) -> HomeContract.WeatherState)
    fun loadWeatherData()
    fun onEventDispatcher(intent: HomeContract.Intent)
}