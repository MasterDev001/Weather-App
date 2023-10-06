package com.example.weatherapp.app.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.weatherapp.R
import com.example.weatherapp.app.screens.SmallItem
import com.example.weatherapp.app.screens.cornerRadius_40
import com.example.weatherapp.app.screens.fontSize_24
import com.example.weatherapp.app.screens.fontSize_34
import com.example.weatherapp.app.screens.lottieSize_220
import com.example.weatherapp.app.screens.padding_32
import com.example.weatherapp.app.screens.padding_5
import com.example.weatherapp.app.screens.topPadding_55
import com.example.weatherapp.presentation.view_models.home.HomeViewModel
import com.example.weatherapp.presentation.view_models.home.HomeViewModelImpl
import com.example.weatherapp.ui.theme.backgroundColor
import com.example.weatherapp.ui.theme.bottomBarColor
import com.example.weatherapp.ui.theme.colorWhite
import com.example.weatherapp.ui.theme.textColorHourly
import java.time.LocalDateTime


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "NewApi")
@Composable
fun HomeScreen(city: String, temperature: String) {

    val viewModel: HomeViewModel = hiltViewModel<HomeViewModelImpl>()
    val uiState by viewModel.weatherState.collectAsState()

    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.extreme_day))
    var isHourlyState by remember { mutableStateOf(true) }

    Scaffold(modifier = Modifier.navigationBarsPadding().fillMaxSize(),
        floatingActionButtonPosition = FabPosition.Center,
        contentWindowInsets = WindowInsets.statusBars,     //statusBars padding
        floatingActionButton = {
            FabButton(onClick = {

            })
        }) {

        Box(Modifier.fillMaxSize()) {

            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.back3),
                contentScale = ContentScale.Crop,
                contentDescription = ""
            )

            Column(
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .statusBarsPadding()
                    .fillMaxSize()
            ) {

                Column(
                    Modifier
                        .padding(top = topPadding_55)
                        .fillMaxSize()
                        .weight(470f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = city, fontSize = fontSize_34, color = colorWhite)
                    Text(
                        text = "${uiState.weatherData?.hourlyData?.get(LocalDateTime.now().hour)?.temperature}",
                        fontSize = fontSize_24,
                        fontWeight = FontWeight.Bold,
                        color = colorWhite
                    )

                    LottieAnimation(
                        modifier = Modifier.size(lottieSize_220),
                        composition = composition,
                        isPlaying = true,
                        iterations = LottieConstants.IterateForever
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
                            Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween
                        ) {

                            Row(
                                Modifier
                                    .padding(
                                        start = padding_32, end = padding_32, top = padding_32
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
                                        start = padding_32, end = padding_5, bottom = padding_32
                                    )
                                    .fillMaxWidth()
                            ) {
                                val data = uiState.weatherData
                                if (isHourlyState && data != null) {
                                    items(count = 24) {
                                        val nowHour = LocalDateTime.now().hour
                                        val item = data.hourlyData[it + nowHour]
                                        SmallItem(
                                            text = item.hour, temperature = "${item.temperature}°"
                                        )
                                    }
                                } else {
                                    val data = uiState.weatherData?.dailyData
                                    if (data != null) items(count = 7) {
                                        val item = data[it]
                                        SmallItem(
                                            text = "${item.date.dayOfWeek}".substring(0, 3),
                                            temperature = "${item.temperatureMax}°"
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .weight(66f)
                        .background(bottomBarColor)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {

                        }, colors = IconButtonDefaults.iconButtonColors(contentColor = colorWhite)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.location),
                            contentDescription = "report",
                        )
                    }
                    FabBox()
                    IconButton(
                        onClick = {

                        }, colors = IconButtonDefaults.iconButtonColors(contentColor = colorWhite)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.menu),
                            contentDescription = "menu"
                        )
                    }
                }
            }
            if (uiState.isLoading!!) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(100.dp)
                )
            }
        }
    }
}
