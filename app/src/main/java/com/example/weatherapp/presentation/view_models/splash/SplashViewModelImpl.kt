package com.example.weatherapp.presentation.view_models.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.app.navigation.AppNavigator
import com.example.weatherapp.app.screens.home.HomeScreen
import com.example.weatherapp.app.screens.location.LocationScreen
import com.example.weatherapp.domain.usecase.cityUseCase.GetCurrentCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor(
    private val appNavigator: AppNavigator,
    private val getCurrentCityUseCase: GetCurrentCityUseCase
) : ViewModel(), SplashViewModel {

    init {
        viewModelScope.launch {
            if (getCurrentCityUseCase.invoke() != null) {
                delay(2000)
                appNavigator.replace(HomeScreen())
            } else {
                delay(2000)
                appNavigator.replace(LocationScreen())
            }
        }
    }
}
