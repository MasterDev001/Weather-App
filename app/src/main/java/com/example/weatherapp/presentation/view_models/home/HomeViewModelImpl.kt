package com.example.weatherapp.presentation.view_models.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.common.ResultData
import com.example.weatherapp.domain.usecase.GetWeatherDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val getWeatherDataUseCase: GetWeatherDataUseCase,
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
            getWeatherDataUseCase.invoke(41.0, 23.0).collect {
                reduce { it.copy(isLoading = false) }
                when (it) {
                    is ResultData.Success -> {
                        weatherState.value=HomeContract.WeatherState(weatherData = it.data)
                    }

                    is ResultData.Error -> {
                        weatherState.value = HomeContract.WeatherState(message = it.message)
                    }

                    is ResultData.Loading -> {
                        weatherState.value = HomeContract.WeatherState(isLoading = it.isLoading)
                    }
                }
            }
        }
    }

    override fun onEventDispatcher(intent: HomeContract.Intent) {

    }
}