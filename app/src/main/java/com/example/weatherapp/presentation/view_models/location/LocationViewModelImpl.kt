package com.example.weatherapp.presentation.view_models.location

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.IntentFilter
import android.content.IntentSender
import android.location.GpsStatus
import android.location.LocationManager
import android.util.Log
import androidx.activity.result.IntentSenderRequest
import androidx.core.content.ContextCompat.registerReceiver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.MainActivity
import com.example.weatherapp.common.ResultData
import com.example.weatherapp.domain.usecase.locationUseCase.GetLocationUseCase
import com.example.weatherapp.domain.usecase.cityUseCase.AddCityUseCase
import com.example.weatherapp.domain.usecase.cityUseCase.GetAllCitiesUseCase
import com.example.weatherapp.domain.usecase.cityUseCase.GetCityDataUseCase
import com.example.weatherapp.domain.usecase.locationUseCase.EnableLocationRequestUseC
import com.example.weatherapp.domain.usecase.locationUseCase.IsGpsConnectedUseCase
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsResponse
import com.google.android.gms.location.Priority
import com.google.android.gms.location.SettingsClient
import com.google.android.gms.tasks.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@SuppressLint("MissingPermission")
@HiltViewModel
class LocationViewModelImpl @Inject constructor(
    private val getAllCitiesUseCase: GetAllCitiesUseCase,
    private val addCityUseCase: AddCityUseCase,
    private val getCityDataUseCase: GetCityDataUseCase,
    private val getLocationUseCase: GetLocationUseCase,
    private val isGpsConnectedUseCase: IsGpsConnectedUseCase,
    private val locationRequestUseC: EnableLocationRequestUseC,
    private val context: Application,
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
            is LocationContract.Intent.AddCityWithName -> {
                viewModelScope.launch {
                    async {
                        reduce { it.copy(isLoading = true) }
                        val cityData = getCityDataUseCase.invoke(intent.cityName)
                        addCityUseCase.invoke(cityData.first().copy(isCurrent = true)).collect {
                            reduce { it.copy(isLoading = false) }
                            when (it) {
                                is ResultData.Success -> {

                                }

                                is ResultData.Error -> {
                                    uiState.value = LocationContract.UiState(message = it.message)
                                }

                                is ResultData.Loading -> {
                                    uiState.value =
                                        LocationContract.UiState(isLoading = it.isLoading)
                                }
                            }
                        }
                    }.await()
                    getCityList()
                }
            }

            is LocationContract.Intent.AddCityWithLocation -> {
                viewModelScope.launch {
                    reduce { it.copy(isLoading = true) }
                    delay(5500)
                    getLocationUseCase.invoke()?.let { location ->

                        Log.d("TAG1212", "onEventDispatcher: $location")
                        reduce { it.copy(isLoading = false) }

                    } ?: kotlin.run {
                        Log.d("TAG1212", "err: ")

                    }
                }
            }
        }
    }

    override fun getCityList() {
        viewModelScope.launch {
            reduce { it.copy(isLoading = true) }
            getAllCitiesUseCase.invoke().collect {
                reduce { it.copy(isLoading = false) }
                when (it) {
                    is ResultData.Success -> {
                        uiState.value = LocationContract.UiState(cityData = it.data)
                    }

                    is ResultData.Error -> {
                        uiState.value = LocationContract.UiState(message = it.message)
                    }

                    is ResultData.Loading -> {
                        uiState.value = LocationContract.UiState(isLoading = it.isLoading)
                    }
                }
            }
        }
    }
}