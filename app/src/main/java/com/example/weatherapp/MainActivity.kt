package com.example.weatherapp

import android.content.IntentFilter
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.example.weatherapp.app.screens.location.LocationScreen
import com.example.weatherapp.presentation.view_models.location.LocationProviderChangedReceiver
import com.example.weatherapp.presentation.view_models.location.LocationViewModel
import com.example.weatherapp.presentation.view_models.location.LocationViewModelImpl
//import com.example.weatherapp.app.screens.getWeatherApp

import com.example.weatherapp.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)      //////

        setContent {
            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,

                    ) {
//                    HomeScreen("Istanbul, Tr", "19°")
//                    ReportScreen()
                    LocationScreen()
//                    InfoScreen()
                }
            }
        }
    }
}

