package com.example.weatherapp.presentation.view_models.home

import com.example.weatherapp.domain.model.CityData
import com.example.weatherapp.domain.model.WeatherData

interface HomeContract {

    sealed interface Intent {
        object NavigateToLocationScreen : Intent

        object NavigateToReportScreen : Intent
        object NavigateToInfoScreen : Intent
    }

    data class WeatherState(
        var weatherData: WeatherData? = null,
        var isLoading: Boolean? = false,
        var message: String? = null,
    )

}