package com.example.weatherapp.presentation.view_models.location

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.IntentFilter
import android.location.LocationManager
import android.util.Log
import androidx.activity.result.IntentSenderRequest
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.R
import com.example.weatherapp.app.navigation.AppNavigator
import com.example.weatherapp.app.screens.home.HomeScreen
import com.example.weatherapp.common.ResultData
import com.example.weatherapp.domain.usecase.locationUseCase.GetLocationUseCase
import com.example.weatherapp.domain.usecase.cityUseCase.AddCityUseCase
import com.example.weatherapp.domain.usecase.cityUseCase.DeleteCityUseCase
import com.example.weatherapp.domain.usecase.cityUseCase.GetAllCitiesUseCase
import com.example.weatherapp.domain.usecase.cityUseCase.SelectCurrentCityUseCase
import com.example.weatherapp.domain.usecase.locationUseCase.EnableLocationRequestUseC
import com.example.weatherapp.domain.usecase.locationUseCase.IsGpsConnectedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("MissingPermission")
@HiltViewModel
class LocationViewModelImpl @Inject constructor(
    private val getAllCitiesUseCase: GetAllCitiesUseCase,
    private val addCityUseCase: AddCityUseCase,
    private val getLocationUseCase: GetLocationUseCase,
    private val isGpsConnectedUseCase: IsGpsConnectedUseCase,
    private val locationRequestUseC: EnableLocationRequestUseC,
    private val selectCurrentCityUseCase: SelectCurrentCityUseCase,
    private val deleteCityUseCase: DeleteCityUseCase,
    private val context: Application,
    private val appNavigator: AppNavigator
) : ViewModel(), LocationViewModel {

    override val uiState = MutableStateFlow(LocationContract.UiState(isLoading = false))
    override val isLocationEnabled = MutableStateFlow(false)

    private var broadCast: LocationProviderChangedReceiver? = null

    init {
        updateLocationServiceStatus()
        registerBroadcastReceiver()
        getCityList()
    }

    private fun registerBroadcastReceiver() {
        broadCast = LocationProviderChangedReceiver()
        broadCast!!.init(
            object : LocationProviderChangedReceiver.LocationListener {
                override fun onEnabled() {
                    isLocationEnabled.value = true//Update our VM
                }

                override fun onDisabled() {
                    isLocationEnabled.value = false//Update our VM
                }
            }
        )
        val filter = IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION)
        context.registerReceiver(broadCast, filter)
    }


    override fun enableLocationRequest(
        context: Context,
        makeRequest: (intentSenderRequest: IntentSenderRequest) -> Unit//Lambda to call when locations are off.
    ) {
        viewModelScope.launch(Dispatchers.IO) { locationRequestUseC.invoke(makeRequest) }
    }

    override fun updateLocationServiceStatus() {
        viewModelScope.launch(Dispatchers.IO) {
            isLocationEnabled.value = isGpsConnectedUseCase.invoke()
        }
    }

    override fun reduce(action: (LocationContract.UiState) -> LocationContract.UiState) {
        val oldState = uiState.value
        uiState.value = action(oldState)
    }

    override fun onEventDispatcher(intent: LocationContract.Intent) {
        when (intent) {
            is LocationContract.Intent.ChangeCurrentCity -> {
                viewModelScope.launch {
                    selectCurrentCityUseCase.invoke(intent.cityKey).collect { result ->
                        when (result) {
                            is ResultData.Success -> {
                                appNavigator.replaceAll(HomeScreen())
                            }

                            is ResultData.Error -> {
                                uiState.value = LocationContract.UiState(errorMessage = result.message)
                            }

                            is ResultData.Loading -> {
                                uiState.value =
                                    LocationContract.UiState(isLoading = result.isLoading)
                            }
                        }
                    }
                }
            }

            is LocationContract.Intent.AddCityWithName -> {
                viewModelScope.launch {
                    reduce { it.copy(isLoading = true) }
                    addCityUseCase.invoke(intent.cityName).collect { result ->
                        reduce { it.copy(isLoading = false) }
                        when (result) {
                            is ResultData.Success -> {
                                getCityList()
                            }

                            is ResultData.Error -> {
                                uiState.value = LocationContract.UiState(errorMessage = result.message)
                            }

                            is ResultData.Loading -> {
                                uiState.value =
                                    LocationContract.UiState(isLoading = result.isLoading)
                            }
                        }
                    }
                }
            }

            is LocationContract.Intent.AddCityWithLocation -> {
                viewModelScope.launch {
                    reduce { it.copy(isLoading = true) }
                    delay(5500)
                    getLocationUseCase.invoke()?.let { location ->
                        addCityUseCase.invoke("${location.latitude},${location.longitude}")
                            .collect { result ->
                                reduce { it.copy(isLoading = false) }
                                when (result) {
                                    is ResultData.Success -> {
                                        getCityList()
                                    }

                                    is ResultData.Error -> {
                                        uiState.value =
                                            LocationContract.UiState(errorMessage = result.message)
                                    }

                                    is ResultData.Loading -> {
                                        uiState.value =
                                            LocationContract.UiState(isLoading = result.isLoading)
                                    }
                                }
                            }
                    } ?: kotlin.run {
                        reduce { it.copy(isLoading = false, errorMessage = context.getString(R.string.couldn_t_retrieve_location)) }
                    }
                }
            }

            is LocationContract.Intent.NavigateToHome -> {
                viewModelScope.launch { appNavigator.replaceAll(HomeScreen()) }
            }

            is LocationContract.Intent.DeleteCity -> {
                viewModelScope.launch {
                    reduce { it.copy(isLoading = true) }
                    deleteCityUseCase.invoke(
                        intent.cityData,
                        intent.index,
                        uiState.value.cityData!!
                    ).collect { result ->
                        reduce { it.copy(isLoading = false) }
                        when (result) {
                            is ResultData.Success -> {
                                getCityList()
                            }

                            is ResultData.Error -> {
                                uiState.value = LocationContract.UiState(errorMessage = result.message)
                            }

                            is ResultData.Loading -> {
                                uiState.value =
                                    LocationContract.UiState(isLoading = result.isLoading)
                            }
                        }
                    }
                }
            }
        }
    }

    override fun getCityList() {
        viewModelScope.launch {
            reduce { it.copy(isLoading = true) }
            getAllCitiesUseCase.invoke().collect { result ->
                reduce { it.copy(isLoading = false) }
                when (result) {
                    is ResultData.Success -> {
                        uiState.value = LocationContract.UiState(cityData = result.data)
                    }

                    is ResultData.Error -> {
                        uiState.value = LocationContract.UiState(errorMessage = result.message)
                    }

                    is ResultData.Loading -> {
                        uiState.value = LocationContract.UiState(isLoading = result.isLoading)
                    }
                }
            }
        }
    }
}