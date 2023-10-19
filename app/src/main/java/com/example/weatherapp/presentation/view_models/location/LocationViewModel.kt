package com.example.weatherapp.presentation.view_models.location

import android.content.Context
import androidx.activity.result.IntentSenderRequest
import com.example.weatherapp.domain.model.CityData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface LocationViewModel {

    val uiState: StateFlow<LocationContract.UiState>
    val isLocationEnabled: StateFlow<Boolean>
    val cityLst: StateFlow<List<CityData>>

    fun enableLocationRequest(
        context: Context,
        makeRequest: (intentSenderRequest: IntentSenderRequest) -> Unit
    )
    fun updateLocationServiceStatus()


    fun reduce(action: (LocationContract.UiState) -> LocationContract.UiState)
    fun onEventDispatcher(intent: LocationContract.Intent)
    fun getCityList()
}