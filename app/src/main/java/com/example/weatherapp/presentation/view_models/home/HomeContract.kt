package com.example.weatherapp.presentation.view_models.home

import com.example.weatherapp.domain.model.DailyData
import com.example.weatherapp.domain.model.HourlyData
import com.example.weatherapp.domain.model.WeatherData

interface HomeContract {

    sealed interface Intent {
        object NavigateToLocationScreen : Intent
        object NavigateToReportScreen : Intent
        object NavigateToInfoScreen : Intent
        class NavigateToHourInfoScreen(val hourlyData: HourlyData):Intent
        class NavigateToDayInfoScreen(val dailyData: DailyData):Intent
        object OnRefresh:Intent
    }

    data class WeatherState(
        var weatherData: WeatherData? = null,
        var isLoading: Boolean = false,
        var errorMessage: String? = null,
    )

}