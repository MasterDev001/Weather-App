package com.example.weatherapp.app.screens.location

import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.core.content.ContextCompat

@Composable
fun locationRequestLauncher(onClickOk:()->Unit,onCancel: () -> Unit): ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult> {
    return rememberLauncherForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { activityResult ->
        if (activityResult.resultCode == ComponentActivity.RESULT_OK) {
//                    mapsViewModel.updateCurrentLocationData(this)//If the user clicks OK to turn on location
        onClickOk()
        } else {
            onCancel()
        }
    }
}

@Composable
fun requestPermissionLauncher(): ManagedActivityResultLauncher<Array<String>, Map<String,Boolean>> {

    val requestPermissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions(),
            onResult = {
//                if (isGranted) {
//                    // Permission granted, update the location
////                    getCurrentLocation(context) { lat, long ->
////                        Log.d("TAG1212", "SearchRow: $lat")
////                    }
//                } else {
////                    alertState = true
                }
            )
    return requestPermissionLauncher
}


fun hasLocationPermission(context: Context): Boolean {
    return ContextCompat.checkSelfPermission(
        context,
        android.Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED
}


//
//fun openLocationSettings(activity: Context) {
//    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
//    val uri = Uri.fromParts("package", activity.packageName, null)
//    intent.data = uri
//    activity.startActivity(intent)
//}