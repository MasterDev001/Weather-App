package com.example.weatherapp.entity.location

import android.annotation.SuppressLint
import android.content.Context
import android.content.IntentSender
import android.location.Location
import android.location.LocationManager
import android.util.Log
import androidx.activity.result.IntentSenderRequest
import com.example.weatherapp.common.ResultData
import com.example.weatherapp.domain.toCityEntity
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsResponse
import com.google.android.gms.location.Priority
import com.google.android.gms.location.SettingsClient
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume


class LocationTrackerImpl(
    private val context: Context,
    private val locationClient: FusedLocationProviderClient
) : LocationTracker {

    @SuppressLint("MissingPermission")
    override suspend fun getCurrentLocation(): Location? {
        return suspendCancellableCoroutine { cont ->
            locationClient.lastLocation.apply {
                if (isComplete) {
                    if (isSuccessful) {
                        cont.resume(result)
                    } else {
                        cont.resume(null)
                    }
                    return@suspendCancellableCoroutine
                }
            }.addOnSuccessListener {
                cont.resume(it)
            }.addOnFailureListener {
                cont.resume(null)
            }.addOnCanceledListener {
                cont.cancel()
            }
        }
//        locationClient.lastLocation
//            .addOnSuccessListener { location ->
//                if (location != null) {
//                    val lat = location.latitude
//                    val long = location.longitude
//                    callback(lat, long)
//                }
//                Log.d("TAG1212", "r$location")
//            }
//            .addOnFailureListener { exception ->
//                // Handle location retrieval failure
//                Log.d("TAG1212", "e $exception")
//                exception.printStackTrace()
//            }
    }

    override fun isGpsConnected(): Boolean {
        val locationManager =
            context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    override fun enableLocationRequest(makeRequest: (intentSenderRequest: IntentSenderRequest) -> Unit) {

        val locationRequest = LocationRequest.create()
            .setPriority(Priority.PRIORITY_HIGH_ACCURACY).setInterval(5000)

        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)

        val client: SettingsClient = LocationServices.getSettingsClient(context)
        val task: Task<LocationSettingsResponse> =
            client.checkLocationSettings(builder.build())//Checksettings with building a request
        task.addOnSuccessListener { locationSettingsResponse ->
            Log.d(
                "TAG1212",
                "enableLocationRequest: LocationService Already Enabled"
            )
        }
        task.addOnFailureListener { exception ->
            if (exception is ResolvableApiException) {
                // Location settings are not satisfied, but this can be fixed
                // by showing the user a dialog.
                try {
                    val intentSenderRequest =
                        IntentSenderRequest.Builder(exception.resolution)
                            .build()
                    makeRequest(intentSenderRequest)
                } catch (sendEx: IntentSender.SendIntentException) {
                    // Ignore the error.
                }
            }
        }
    }
}