package com.example.weatherapp.app.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.hilt.getViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.weatherapp.R
import com.example.weatherapp.app.navigation.AppScreen
import com.example.weatherapp.app.screens.lottieSize_220
import com.example.weatherapp.presentation.view_models.splash.SplashViewModel
import com.example.weatherapp.presentation.view_models.splash.SplashViewModelImpl
import com.example.weatherapp.ui.theme.backgroundColor

class SplashScreen : AppScreen() {

    @Composable
    override fun Content() {
        val viewModel: SplashViewModel = getViewModel<SplashViewModelImpl>()
        SplashScreenContent(viewModel)
    }

    @Composable
    private fun SplashScreenContent(viewModel: SplashViewModel) {

        val composition by rememberLottieComposition(
            spec = LottieCompositionSpec.RawRes(R.raw.sun)
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor), contentAlignment = Alignment.Center
        ) {
            LottieAnimation(
                modifier = Modifier.size(lottieSize_220),
                composition = composition,
                isPlaying = true,
                iterations = LottieConstants.IterateForever
            )
        }


    }
}