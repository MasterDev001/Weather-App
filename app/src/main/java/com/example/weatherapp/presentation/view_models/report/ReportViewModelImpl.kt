package com.example.weatherapp.presentation.view_models.report

import com.example.weatherapp.app.navigation.AppNavigator
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.app.screens.info.InfoScreen
import com.example.weatherapp.domain.toInfoData
import com.example.weatherapp.domain.toInfoDataForMain
import com.example.weatherapp.presentation.view_models.home.HomeContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReportViewModelImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : ViewModel(), ReportViewModel {

    override fun onEventDispatcher(intent: ReportContract.Intent) {
        when (intent) {
            is ReportContract.Intent.NavigateToHomeScreen -> {
                viewModelScope.launch { appNavigator.back() }
            }
            is ReportContract.Intent.NavigateToHourInfoScreen -> {
                viewModelScope.launch { appNavigator.navigateTo(InfoScreen(intent.hourlyData.toInfoDataForMain())) }
            }
            is ReportContract.Intent.NavigateToDayInfoScreen -> {
                viewModelScope.launch { appNavigator.navigateTo(InfoScreen(intent.dailyData.toInfoData())) }
            }
        }
    }
}