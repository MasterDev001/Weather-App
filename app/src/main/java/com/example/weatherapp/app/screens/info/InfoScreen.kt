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
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
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
import com.example.weatherapp.ui.theme.backgroundColor
import com.example.weatherapp.ui.theme.colorWhite
import com.example.weatherapp.ui.theme.weatherStatusTextColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun InfoScreen() {

    var offset by remember { mutableStateOf(Offset.Zero) }

    Scaffold(
        Modifier.navigationBarsPadding().fillMaxSize(),
        containerColor = backgroundColor,

        ) {
        Box(modifier = Modifier.fillMaxSize()) {

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
                        painter = painterResource(id = R.drawable.moon_cloud_mid_rain),
                        contentDescription = "",
                    )

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Mid Rain",
                            color = weatherStatusTextColor,
                            fontSize = fontSize_15
                        )
                        Text(
                            text = "18°",
                            color = colorWhite,
                            fontSize = fontSize_52,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Row(
                    Modifier
                        .padding(start = padding_16, top = padding_22, end = padding_16)
                        .fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(padding_38)
                ) {
                    MiddleItem(
                        Modifier.weight(1f),
                        icon = R.drawable.uv_index,
                        title = stringResource(R.string.uv_index),
                        text = "3"
                    )
                    MiddleItem(
                        Modifier.weight(1f),
                        icon = R.drawable.cloud,
                        title = stringResource(R.string.precipitation),
                        text = "4.0 mm"
                    )
                }
                Row(
                    Modifier
                        .padding(start = padding_16, top = padding_16, end = padding_16)
                        .fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(padding_38)
                ) {
                    MiddleItem(
                        Modifier.weight(1f),
                        icon = R.drawable.visibility,
                        title = stringResource(R.string.visibility),
                        text = "5 km"
                    )
                    MiddleItem(
                        Modifier.weight(1f),
                        icon = R.drawable.pressure,
                        title = stringResource(R.string.pressure),
                        text = "900 hpa"
                    )
                }
                Row(
                    Modifier
                        .padding(start = padding_16, top = padding_16, end = padding_16)
                        .fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(padding_38)
                ) {
                    MiddleItem(
                        Modifier.weight(1f),
                        icon = R.drawable.humidity,
                        title = stringResource(R.string.humidity),
                        text = "80%"
                    )
                    MiddleItem(
                        Modifier.weight(1f),
                        icon = R.drawable.wind,
                        title = stringResource(R.string.wind),
                        text = "4 km/h"
                    )
                }
                Row(
                    Modifier
                        .padding(start = padding_16, top = padding_16, end = padding_16)
                        .fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(padding_38)
                ) {
                    MiddleItem(
                        Modifier.weight(1f),
                        icon = R.drawable.sunrise,
                        title = stringResource(R.string.sunrise),
                        text = "6:30 AM"
                    )
                    MiddleItem(
                        Modifier.weight(1f),
                        icon = R.drawable.sunset,
                        title = stringResource(R.string.sunset),
                        text = "7:30 PM"
                    )
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