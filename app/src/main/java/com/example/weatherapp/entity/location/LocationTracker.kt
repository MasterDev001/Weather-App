package com.example.weatherapp.entity.location

import android.content.Context
import android.location.Location
import androidx.activity.result.IntentSenderRequest

interface LocationTracker {

    suspend fun getCurrentLocation(): Location?
    fun isGpsConnected(): Boolean
    fun enableLocationRequest(makeRequest: (intentSenderRequest: IntentSenderRequest) -> Unit)//Lambda to call when locations are off.
}