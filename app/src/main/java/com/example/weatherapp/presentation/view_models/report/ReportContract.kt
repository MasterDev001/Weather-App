package com.example.weatherapp.presentation.view_models.report

import com.example.weatherapp.domain.model.CityData
import com.example.weatherapp.domain.model.DailyData
import com.example.weatherapp.domain.model.HourlyData

interface ReportContract {

    sealed interface Intent {
        object NavigateToHomeScreen : Intent
        class NavigateToHourInfoScreen(val hourlyData: HourlyData): Intent
        class NavigateToDayInfoScreen(val dailyData: DailyData): Intent
    }

}