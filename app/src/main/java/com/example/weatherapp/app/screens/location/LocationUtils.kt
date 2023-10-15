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
import com.example.weatherapp.presentation.view_models.location.LocationContract
import com.example.weatherapp.presentation.view_models.location.LocationViewModel

@Composable
fun locationRequestLauncher(
    onClickOk: () -> Unit,
    onCancel: () -> Unit
): ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult> {
    return rememberLauncherForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { activityResult ->
        if (activityResult.resultCode == ComponentActivity.RESULT_OK) {
//                    viewModel.updateCurrentLocationData(this)//If the user clicks OK to turn on location
            onClickOk()
        } else {
            onCancel()
        }
    }
}

@Composable
fun requestPermissionLauncher(
    onClickOk: () -> Unit,
    onClickDeny: () -> Unit
): ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>> {

    val requestPermissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions(),
            onResult = {
                if (it.values.all { it }) {
                    // Permission granted, update the location
                    onClickOk()
                } else {
                    onClickDeny()
                }
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


fun onPermissionAccessClicked(
    isLocationEnabled: Boolean,
    viewModel: LocationViewModel,
    context: Context,
    locationRequestLauncher: ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>,
    onEvent: (intent: LocationContract.Intent) -> Unit
) {
    if (!isLocationEnabled) {
        viewModel.enableLocationRequest(context) {//Call this if GPS is OFF.
            locationRequestLauncher.launch(it)//Launch it to show the prompt.
        }
    } else {
        onEvent.invoke(LocationContract.Intent.AddCityWithLocation)
    }
}

//
//fun openLocationSettings(activity: Context) {
//    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
//    val uri = Uri.fromParts("package", activity.packageName, null)
//    intent.data = uri
//    activity.startActivity(intent)
//}