package com.example.weatherapp.app.screens.info

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.hilt.getViewModel
import com.example.weatherapp.R
import com.example.weatherapp.app.navigation.AppScreen
import com.example.weatherapp.app.screens.ElevationCustom
import com.example.weatherapp.app.screens.customElevationSize_200
import com.example.weatherapp.app.screens.customElevationSize_250
import com.example.weatherapp.app.screens.fontSize_15
import com.example.weatherapp.app.screens.fontSize_52
import com.example.weatherapp.app.screens.imageSize_150
import com.example.weatherapp.app.screens.padding_16
import com.example.weatherapp.app.screens.padding_22
import com.example.weatherapp.app.screens.padding_38
import com.example.weatherapp.app.screens.topAppBarHeight_64
import com.example.weatherapp.domain.model.InfoData
import com.example.weatherapp.presentation.view_models.info.InfoContract
import com.example.weatherapp.presentation.view_models.info.InfoViewModel
import com.example.weatherapp.presentation.view_models.info.InfoViewModelImpl
import com.example.weatherapp.ui.theme.backgroundColor
import com.example.weatherapp.ui.theme.colorWhite
import com.example.weatherapp.ui.theme.weatherStatusTextColor

class InfoScreen(private val infoData: InfoData) : AppScreen() {

    @Composable
    override fun Content() {
        val viewModel: InfoViewModel = getViewModel<InfoViewModelImpl>()
        val onEvent = viewModel::onEventDispatcher
        InfoScreenContent(viewModel, onEvent)
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun InfoScreenContent(
        viewModel: InfoViewModel,
        onEvent: (intent: InfoContract.Intent) -> Unit
    ) {

        var offset by remember { mutableStateOf(Offset.Zero) }

        Scaffold(
            Modifier.fillMaxSize(),
            containerColor = backgroundColor,

            ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .navigationBarsPadding()
            ) {

                if (offset != Offset.Zero) ElevationCustom(
                    modifier = Modifier
                        .size(customElevationSize_200)
                        .align(Alignment.TopStart)
                        .offset { IntOffset(offset.x.toInt() - 65, offset.y.toInt() - 60) },
                    color = colorWhite.copy(alpha = 0.4f)
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
                            onEvent.invoke(InfoContract.Intent.NavigateToHomeScreen)
                        }) {
                            Icon(
                                painterResource(id = R.drawable.arrow2),
                                contentDescription = "back",
                                tint = colorWhite,
                            )
                        }
                    }
                    Row(
                        Modifier
                            .padding(
                                start = padding_22, end = padding_38
                            )
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier
                                .size(imageSize_150)
                                .onGloballyPositioned { offset = it.boundsInRoot().topLeft },
                            painter = painterResource(id = infoData.condition.icon),
                            contentDescription = "",
                        )

                        Column(
                            modifier = Modifier.padding(start = padding_16),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = infoData.condition.desc,
                                color = weatherStatusTextColor,
                                fontSize = fontSize_15,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = "${infoData.temperature}°",
                                color = colorWhite,
                                fontSize = fontSize_52,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Row(
                        Modifier
                            .padding(start = padding_16, top = padding_22, end = padding_16)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(padding_38)
                    ) {
                        InfoItem(
                            Modifier.weight(1f),
                            icon = R.drawable.uv_index,
                            title = stringResource(R.string.uv_index),
                            text = infoData.uv.toString()
                        )
                        InfoItem(
                            Modifier.weight(1f),
                            icon = R.drawable.cloud,
                            title = stringResource(R.string.precipitation),
                            text = "${infoData.precipitation} mm"
                        )
                    }
                    Row(
                        Modifier
                            .padding(start = padding_16, top = padding_16, end = padding_16)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(padding_38)
                    ) {
                        InfoItem(
                            Modifier.weight(1f),
                            icon = R.drawable.visibility,
                            title = stringResource(R.string.visibility),
                            text = "${infoData.visibility} km"
                        )
                        if (infoData.snow != null) {
                            InfoItem(
                                Modifier.weight(1f),
                                icon = R.drawable.snow,
                                title = stringResource(R.string.snow),
                                text = "${infoData.snow} cm"
                            )
                        } else {
                            InfoItem(
                                Modifier.weight(1f),
                                icon = R.drawable.pressure,
                                title = stringResource(R.string.pressure),
                                text = "${infoData.pressure} hpa"
                            )
                        }
                    }
                    Row(
                        Modifier
                            .padding(start = padding_16, top = padding_16, end = padding_16)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(padding_38)
                    ) {
                        InfoItem(
                            Modifier.weight(1f),
                            icon = R.drawable.humidity,
                            title = stringResource(R.string.humidity),
                            text = "${infoData.humidity}%"
                        )
                        InfoItem(
                            Modifier.weight(1f),
                            icon = R.drawable.wind,
                            title = stringResource(R.string.wind),
                            text = "${infoData.windSpeed} km/h"
                        )
                    }
                    if (infoData.sunRise != null) {
                        Row(
                            Modifier
                                .padding(start = padding_16, top = padding_16, end = padding_16)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(padding_38)
                        ) {
                            InfoItem(
                                Modifier.weight(1f),
                                icon = R.drawable.sunrise,
                                title = stringResource(R.string.sunrise),
                                text = infoData.sunRise
                            )
                            InfoItem(
                                Modifier.weight(1f),
                                icon = R.drawable.sunset,
                                title = stringResource(R.string.sunset),
                                text = infoData.sunSet!!
                            )
                        }
                    }
                }
                ElevationCustom(
                    modifier = Modifier
                        .size(customElevationSize_250)
                        .align(Alignment.BottomStart)
                        .offset(x = (-120).dp, y = (110).dp), color = colorWhite.copy(alpha = 0.2f)
                )
            }
        }
    }
}
