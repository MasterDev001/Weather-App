package com.example.weatherapp.domain.usecase.locationUseCase

import com.example.weatherapp.entity.location.LocationTracker
import javax.inject.Inject

class IsGpsConnectedUseCase @Inject constructor(private val locationTracker: LocationTracker) {

    suspend operator fun invoke()=locationTracker.isGpsConnected()
}