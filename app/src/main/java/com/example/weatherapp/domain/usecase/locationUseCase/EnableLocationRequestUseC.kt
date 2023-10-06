package com.example.weatherapp.domain.usecase.locationUseCase

import android.content.Context
import androidx.activity.result.IntentSenderRequest
import com.example.weatherapp.entity.location.LocationTracker
import javax.inject.Inject

class EnableLocationRequestUseC @Inject constructor(private val locationTracker: LocationTracker) {

    suspend operator fun invoke(
        makeRequest: (intentSenderRequest: IntentSenderRequest) -> Unit
    ) = locationTracker.enableLocationRequest(makeRequest)
}