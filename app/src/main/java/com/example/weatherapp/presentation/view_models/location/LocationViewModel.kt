package com.example.weatherapp.presentation.view_models.location

import android.content.Context
import androidx.activity.result.IntentSenderRequest
import kotlinx.coroutines.flow.StateFlow

interface LocationViewModel {

    val uiState: StateFlow<LocationContract.UiState>
    val isLocationEnabled: StateFlow<Boolean>

    fun enableLocationRequest(
        context: Context,
        makeRequest: (intentSenderRequest: IntentSenderRequest) -> Unit
    )
    fun updateLocationServiceStatus()


    fun reduce(action: (LocationContract.UiState) -> LocationContract.UiState)
    fun onEventDispatcher(intent: LocationContract.Intent)
    fun getCityList()
}