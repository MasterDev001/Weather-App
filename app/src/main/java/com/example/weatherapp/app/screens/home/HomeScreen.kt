package com.example.weatherapp.app.screens.home

import com.example.weatherapp.app.navigation.AppScreen
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.hilt.getViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.weatherapp.R
import com.example.weatherapp.app.screens.CustomAlertDialog
import com.example.weatherapp.app.screens.HourlyItem
import com.example.weatherapp.app.screens.cornerRadius_40
import com.example.weatherapp.app.screens.fontSize_24
import com.example.weatherapp.app.screens.fontSize_28
import com.example.weatherapp.app.screens.fontSize_34
import com.example.weatherapp.app.screens.imageSize_76
import com.example.weatherapp.app.screens.lottieSize_220
import com.example.weatherapp.app.screens.padding_16
import com.example.weatherapp.app.screens.padding_22
import com.example.weatherapp.app.screens.padding_32
import com.example.weatherapp.app.screens.padding_5
import com.example.weatherapp.app.screens.topPadding_55
import com.example.weatherapp.presentation.view_models.home.HomeContract
import com.example.weatherapp.presentation.view_models.home.HomeViewModel
import com.example.weatherapp.presentation.view_models.home.HomeViewModelImpl
import com.example.weatherapp.ui.theme.backgroundColor
import com.example.weatherapp.ui.theme.bottomBarColor
import com.example.weatherapp.ui.theme.colorWhite
import com.example.weatherapp.ui.theme.fabBackground
import com.example.weatherapp.ui.theme.iconColor
import com.example.weatherapp.ui.theme.textColorHomeCity
import com.example.weatherapp.ui.theme.textColorHourly
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import java.time.LocalDateTime
import java.time.ZoneId


class HomeScreen : AppScreen() {

    @Composable
    override fun Content() {
        val viewModel: HomeViewModel = getViewModel<HomeViewModelImpl>()
        val onEvent = viewModel::onEventDispatcher
        HomeScreenContent(viewModel, onEvent)
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "NewApi")
    @Composable
    private fun HomeScreenContent(
        viewModel: HomeViewModel,
        onEvent: (intent: HomeContract.Intent) -> Unit,
    ) {
        val uiState by viewModel.weatherState.collectAsState()
        val composition by rememberLottieComposition(
            spec = LottieCompositionSpec.RawRes(
                uiState.weatherData?.currentData?.condition?.lottie ?: 0
            )
        )
        var isHourlyState by remember { mutableStateOf(true) }
        val scrollState = rememberScrollState()
        val refreshState = rememberSwipeRefreshState(
            isRefreshing = uiState.isLoading,
        )
        var alertDialogState by rememberSaveable { mutableStateOf(false) }

        LaunchedEffect(key1 = uiState.errorMessage) {
            alertDialogState = true
        }

        if (uiState.errorMessage != null && alertDialogState) {
            CustomAlertDialog(message = uiState.errorMessage!!) {
                alertDialogState = false
                onEvent.invoke(HomeContract.Intent.OnRefresh)
            }
        }
        Scaffold(modifier = Modifier

            .fillMaxSize()
            .clipToBounds(),
            floatingActionButtonPosition = FabPosition.Center,
            contentWindowInsets = WindowInsets.statusBars,     //statusBars padding
            floatingActionButton = {
                FabButton(modifier = Modifier.navigationBarsPadding(), onClick = {
                    onEvent.invoke(HomeContract.Intent.NavigateToLocationScreen)
                })
            }) {

            Box(Modifier.fillMaxSize()) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.back3),
                    contentScale = ContentScale.Crop,
                    contentDescription = ""
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(imageSize_76)
                        .background(bottomBarColor).align(Alignment.BottomStart)
                )

                SwipeRefresh(
                    modifier = Modifier.statusBarsPadding(),
                    state = refreshState,
                    onRefresh = { onEvent.invoke(HomeContract.Intent.OnRefresh) },
                    indicator = { state, triggle ->
                        SwipeRefreshIndicator(
                            state = state,
                            refreshTriggerDistance = triggle,
                            contentColor = iconColor,
                            backgroundColor = fabBackground
                        )
                    },
                ) {
                    Column(
                        verticalArrangement = Arrangement.Bottom,
                        modifier = Modifier
                            .fillMaxSize()
                            .navigationBarsPadding()
                            .verticalScroll(scrollState)
                    ) {

                        Column(
                            Modifier
                                .padding(top = topPadding_55)
                                .fillMaxSize()
                                .weight(470f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            if (uiState.weatherData != null)
                                Text(
                                    text = "${uiState.weatherData!!.locationData.name}, ${uiState.weatherData!!.locationData.country}",
                                    fontSize = fontSize_24,
                                    color = textColorHomeCity,
                                    textAlign = TextAlign.Center
                                )
                            Text(
                                modifier = Modifier.padding(top = padding_16),
                                text = if (uiState.weatherData != null) "${uiState.weatherData!!.currentData.temperature}°" else "",
                                fontSize = fontSize_34,
                                fontWeight = FontWeight.Bold,
                                color = textColorHomeCity
                            )

                            LottieAnimation(
                                modifier = Modifier.size(lottieSize_220),
                                composition = composition,
                                isPlaying = true,
                                iterations = LottieConstants.IterateForever,
                                contentScale = ContentScale.Inside
                            )
                        }
                        Box(modifier = Modifier.weight(270f)) {
                            Box(
                                modifier = Modifier
                                    .background(bottomBarColor)
                                    .fillMaxWidth()
                                    .height(40.dp)
                                    .align(Alignment.BottomCenter)
                            )
                            Card(
                                Modifier
                                    .fillMaxSize()
                                    .align(Alignment.BottomCenter),
                                colors = CardDefaults.cardColors(containerColor = backgroundColor),
                                shape = RoundedCornerShape(cornerRadius_40)
                            ) {
                                Column(
                                    Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.SpaceBetween
                                ) {

                                    Row(
                                        Modifier
                                            .padding(
                                                start = padding_32,
                                                end = padding_32,
                                                top = padding_32
                                            )
                                            .fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            modifier = Modifier.clickable {
                                                isHourlyState = true
                                            },
                                            text = stringResource(R.string.hourly_forecast),
                                            color = textColorHourly
                                        )
                                        Text(
                                            modifier = Modifier.clickable {
                                                isHourlyState = false
                                            },
                                            text = stringResource(R.string.weekly_forecast),
                                            color = textColorHourly
                                        )
                                    }

                                    LazyRow(
                                        modifier = Modifier
                                            .padding(
                                                start = padding_5,
                                                end = padding_5,
                                                bottom = padding_32
                                            )
                                            .fillMaxWidth()
                                    ) {
                                        val data = uiState.weatherData
                                        if (isHourlyState && data != null) {
                                            var isNow = false
                                            var nowHour =
                                                LocalDateTime.now(ZoneId.of(data.locationData.timezoneId)).hour
                                            if (nowHour > 0) {
                                                nowHour -= 1
                                                isNow = true
                                            }
                                            items(count = 24) {
                                                val item = data.hourlyData[it + nowHour]
                                                HourlyItem(
                                                    text = item.hour,
                                                    icon = item.conditionData.icon,
                                                    temperature = "${item.temperature}°",
                                                    onClick = {
                                                        onEvent.invoke(
                                                            HomeContract.Intent.NavigateToHourInfoScreen(
                                                                item
                                                            )
                                                        )
                                                    },
                                                    isNow = isNow && it == 1,
                                                    isFirst = it == 0
                                                )
                                            }
                                        } else {
                                            val data = uiState.weatherData?.dailyData
                                            if (data != null) items(count = data.size) {
                                                val item = data[it]
                                                HourlyItem(
                                                    text = "${item.date.dayOfWeek}".substring(
                                                        0, 3
                                                    ),
                                                    temperature = "${item.temperatureMax}°",
                                                    onClick = {
                                                        onEvent.invoke(
                                                            HomeContract.Intent.NavigateToDayInfoScreen(
                                                                item
                                                            )
                                                        )
                                                    },
                                                    isFirst = it == 0,
                                                    icon = item.conditionData.icon
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        Row(
                            modifier = Modifier
                                .weight(69f)
                                .background(bottomBarColor)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(
                                onClick = {
                                    if (uiState.weatherData != null) onEvent.invoke(HomeContract.Intent.NavigateToReportScreen)
                                },
                                colors = IconButtonDefaults.iconButtonColors(contentColor = colorWhite)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.location),
                                    contentDescription = "report",
                                )
                            }
                            FabBox()
                            IconButton(
                                onClick = {
                                    if (uiState.weatherData != null) onEvent.invoke(HomeContract.Intent.NavigateToInfoScreen)
                                },
                                colors = IconButtonDefaults.iconButtonColors(contentColor = colorWhite)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.menu),
                                    contentDescription = "menu"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
