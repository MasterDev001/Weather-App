package com.example.weatherapp.app.screens.location

import android.Manifest
import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.weatherapp.R
import com.example.weatherapp.app.screens.CustomAlertDialog
import com.example.weatherapp.app.screens.ElevationCustom
import com.example.weatherapp.app.screens.customElevationSize_200
import com.example.weatherapp.app.screens.customElevationSize_250
import com.example.weatherapp.app.screens.fontSize_15
import com.example.weatherapp.app.screens.fontSize_24

import com.example.weatherapp.app.screens.padding_16
import com.example.weatherapp.app.screens.padding_22
import com.example.weatherapp.app.screens.padding_32
import com.example.weatherapp.app.screens.progressBarSize_130
import com.example.weatherapp.app.screens.progressBarStrokeWith_8
import com.example.weatherapp.app.screens.topAppBarHeight_64
import com.example.weatherapp.presentation.view_models.location.LocationContract
import com.example.weatherapp.presentation.view_models.location.LocationViewModel
import com.example.weatherapp.presentation.view_models.location.LocationViewModelImpl
import com.example.weatherapp.ui.theme.backgroundColor
import com.example.weatherapp.ui.theme.blueElevationColor
import com.example.weatherapp.ui.theme.bottomBarColor
import com.example.weatherapp.ui.theme.colorWhite
import com.example.weatherapp.ui.theme.fabBackground
import com.example.weatherapp.ui.theme.iconColor
import com.example.weatherapp.ui.theme.searchTextColor

class LocationScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        val viewModel: LocationViewModel = getViewModel<LocationViewModelImpl>()
        val onEvent = viewModel::onEventDispatcher
        LocationScreenContent(viewModel, onEvent)
    }

    @Composable
    private fun LocationScreenContent(
        viewModel: LocationViewModel, onEvent: (intent: LocationContract.Intent) -> Unit
    ) {

        val uiState by viewModel.uiState.collectAsState()
        var searchText by rememberSaveable { mutableStateOf("") }
        var onLocationClick by rememberSaveable { mutableStateOf(false) }
        val context = LocalContext.current
        val toastText = stringResource(R.string.cities_should_be_no_more_than_5)
        val gpsToastText = stringResource(R.string.location_access_is_mandatory_to_use_this_feature)
        val isLocationEnabled by viewModel.isLocationEnabled.collectAsState()
        val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.trash))
        var alertDialogState by rememberSaveable { mutableStateOf(false) }


        LaunchedEffect(key1 = uiState.errorMessage) {
            alertDialogState = true
        }

        val requestPermissionLauncher = requestPermissionLauncher(onClickOk = {
            onLocationClick = true
        }, onClickDeny = {})

        val locationRequestLauncher = locationRequestLauncher(onCancel = {
            Toast.makeText(context, gpsToastText, Toast.LENGTH_SHORT).show()
        }, onClickOk = {
            onEvent.invoke(LocationContract.Intent.AddCityWithLocation)
        })

        if (onLocationClick) {
            onLocationClick = false
            onPermissionAccessClicked(
                isLocationEnabled, viewModel, context, locationRequestLauncher, onEvent
            )
        }

        BackHandler {
            onEvent.invoke(LocationContract.Intent.NavigateToHome)
        }

        Scaffold(
            Modifier.fillMaxSize(),
            containerColor = backgroundColor,

            ) {

            Box(modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()) {
                ElevationCustom(
                    modifier = Modifier
                        .size(customElevationSize_200)
                        .align(Alignment.CenterEnd)
                        .offset(x = (160).dp, y = (-20).dp),
                    color = blueElevationColor.copy(alpha = 0.2f)
                )

                Column(
                    Modifier
                        .statusBarsPadding()
                        .fillMaxSize()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(topAppBarHeight_64)
                    ) {
                        IconButton(modifier = Modifier
//                            .padding(start = padding_16)
                            .align(Alignment.CenterStart), onClick = {
                            onEvent.invoke(LocationContract.Intent.NavigateToHome)
                        }) {
                            Icon(
                                painterResource(id = R.drawable.arrow2),
                                contentDescription = "back",
                                tint = colorWhite,
                            )
                        }

                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = stringResource(R.string.pick_location),
                            fontSize = fontSize_24,
                            color = colorWhite
                        )
                    }

                    Text(
                        text = stringResource(R.string.search_text),
                        color = searchTextColor,
                        modifier = Modifier.padding(
                            start = padding_32,
                            top = it.calculateTopPadding() + padding_16,
                            end = padding_32
                        ),
                        fontSize = fontSize_15,
                        textAlign = TextAlign.Center
                    )

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(padding_22)
                    )

                    SearchRow(
                        Modifier
                            .padding(horizontal = padding_16)
                            .fillMaxWidth(),
                        searchText = searchText,
                        onSearchClick = {
                            if (uiState.cityData.orEmpty().size < 15) onEvent.invoke(
                                LocationContract.Intent.AddCityWithName(searchText.trim())
                            )
                            else {
                                Toast.makeText(
                                    context, toastText, Toast.LENGTH_SHORT
                                ).show()
                            }
                        },
                        onLocationClick = {
                            if (hasLocationPermission(context)) {
                                onLocationClick = true
                            } else {
                                requestPermissionLauncher.launch(
                                    arrayOf(
                                        Manifest.permission.ACCESS_FINE_LOCATION,
                                        Manifest.permission.ACCESS_COARSE_LOCATION,
                                    )
                                )
                            }
                        }) { searchText = it }

                    if (uiState.errorMessage != null && alertDialogState) {
                        CustomAlertDialog(message = uiState.errorMessage!!) {
                            alertDialogState = false
                        }
                    }
                    if (uiState.isLoading) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(progressBarSize_130),
                                strokeCap = StrokeCap.Round,
                                strokeWidth = progressBarStrokeWith_8,
                                color = iconColor
                            )
                        }
                    } else {
                        if (uiState.cityData != null) {
                            LazyVerticalGrid(
                                modifier = Modifier
                                    .padding(top = padding_22)
                                    .fillMaxWidth(),
                                columns = GridCells.Fixed(2),
                                contentPadding = PaddingValues(padding_16), //layout padding
                                horizontalArrangement = Arrangement.spacedBy(padding_16), ///item between padding
                                verticalArrangement = Arrangement.spacedBy(padding_16)///item between padding
                            ) {
                                items(uiState.cityData.orEmpty().size) {
                                    CityItem(
                                        cityData = uiState.cityData.orEmpty()[it],
                                        onClick = {
                                            onEvent.invoke(
                                                LocationContract.Intent.ChangeCurrentCity(
                                                    uiState.cityData!![it].key
                                                )
                                            )
                                        }, composition = composition,
                                        onDelete = { cityData ->
                                            onEvent.invoke(
                                                LocationContract.Intent.DeleteCity(
                                                    cityData, it
                                                )
                                            )
                                        }, isDeletable = uiState.cityData!!.size > 1
                                    )
                                }
                            }
                        }
                    }
                }
                ElevationCustom(
                    modifier = Modifier
                        .size(customElevationSize_200)
                        .align(Alignment.CenterEnd)
                        .offset(x = (140).dp, y = (-20).dp),
                    color = blueElevationColor.copy(alpha = 0.5f)
                )
                ElevationCustom(
                    modifier = Modifier
                        .size(customElevationSize_250)
                        .align(Alignment.BottomStart)
                        .offset(x = (-120).dp, y = (110).dp),
                    color = blueElevationColor.copy(alpha = 0.7f)
                )
            }
        }
    }
}
