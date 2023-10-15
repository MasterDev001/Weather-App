package com.example.weatherapp.presentation.view_models.home

import com.example.weatherapp.app.navigation.AppNavigator
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.app.screens.info.InfoScreen
import com.example.weatherapp.app.screens.location.LocationScreen
import com.example.weatherapp.app.screens.report.ReportScreen
import com.example.weatherapp.common.ResultData
import com.example.weatherapp.domain.toInfoData
import com.example.weatherapp.domain.toInfoDataForMain
import com.example.weatherapp.domain.usecase.GetWeatherDataUseCase
import com.example.weatherapp.domain.usecase.cityUseCase.GetCurrentCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val getWeatherDataUseCase: GetWeatherDataUseCase,
    private val getCurrentCityUseCase: GetCurrentCityUseCase,
    private val appNavigator: AppNavigator
) : ViewModel(), HomeViewModel {

    override val weatherState = MutableStateFlow(HomeContract.WeatherState(isLoading = false))

    init {
        loadWeatherData()
    }

    override fun reduce(action: (HomeContract.WeatherState) -> HomeContract.WeatherState) {
        val oldState = weatherState.value
        weatherState.value = action(oldState)
    }

    override fun loadWeatherData() {
        viewModelScope.launch {
            reduce { it.copy(isLoading = true) }
            val currentCity = getCurrentCityUseCase.invoke()
            getWeatherDataUseCase.invoke("${currentCity!!.cityName} ${currentCity.region} ${currentCity.country}")
                .collect { result ->
                    reduce { it.copy(isLoading = false) }
                    when (result) {
                        is ResultData.Success -> {
                            weatherState.value =
                                HomeContract.WeatherState(weatherData = result.data)
                        }

                        is ResultData.Error -> {
                            weatherState.value =
                                HomeContract.WeatherState(errorMessage = result.message)
                        }

                        is ResultData.Loading -> {
                            weatherState.value =
                                HomeContract.WeatherState(isLoading = result.isLoading)
                        }
                    }
                }
        }
    }

    override fun onEventDispatcher(intent: HomeContract.Intent) {
        when (intent) {
            is HomeContract.Intent.NavigateToInfoScreen -> {
                viewModelScope.launch {
                    appNavigator.navigateTo(InfoScreen(weatherState.value.weatherData!!.toInfoDataForMain()))
                }
            }

            is HomeContract.Intent.NavigateToLocationScreen -> {
                viewModelScope.launch {
                    appNavigator.navigateTo(LocationScreen())
                }
            }

            is HomeContract.Intent.NavigateToReportScreen -> {
                viewModelScope.launch {
                    appNavigator.navigateTo(ReportScreen(weatherState.value.weatherData!!))
                }
            }

            is HomeContract.Intent.OnRefresh -> {
                viewModelScope.launch {
                    reduce { it.copy(isLoading = true) }
                    delay(2500)
                    loadWeatherData()
                }
            }

            is HomeContract.Intent.NavigateToHourInfoScreen -> {
                viewModelScope.launch { appNavigator.navigateTo(InfoScreen(intent.hourlyData.toInfoDataForMain())) }
            }

            is HomeContract.Intent.NavigateToDayInfoScreen -> {
                viewModelScope.launch { appNavigator.navigateTo(InfoScreen(intent.dailyData.toInfoData())) }
            }
        }
    }
}