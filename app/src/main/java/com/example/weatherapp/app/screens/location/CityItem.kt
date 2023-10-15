package com.example.weatherapp.app.screens.location

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.example.weatherapp.R
import com.example.weatherapp.app.screens.card_Sizes_170
import com.example.weatherapp.app.screens.cornerRadius_32
import com.example.weatherapp.app.screens.fontSize_12
import com.example.weatherapp.app.screens.fontSize_15
import com.example.weatherapp.app.screens.fontSize_28
import com.example.weatherapp.app.screens.fontSize_34
import com.example.weatherapp.app.screens.imageSize_52
import com.example.weatherapp.app.screens.imageSize_58
import com.example.weatherapp.app.screens.lottieSize_24
import com.example.weatherapp.app.screens.padding_16
import com.example.weatherapp.app.screens.padding_3
import com.example.weatherapp.domain.model.CityData
import com.example.weatherapp.ui.theme.backgroundColor
import com.example.weatherapp.ui.theme.colorWhite
import com.example.weatherapp.ui.theme.currentCityCornerColor
import com.example.weatherapp.ui.theme.currentCityItemColor
import com.example.weatherapp.ui.theme.currentItemSecondColor
import com.example.weatherapp.ui.theme.currentItemTempColor
import com.example.weatherapp.ui.theme.placeHolderColor
import com.example.weatherapp.ui.theme.hourlyItemBackgroundColor
import com.example.weatherapp.ui.theme.hourlyItemCornerColor
import com.example.weatherapp.ui.theme.dailyItemCornerColor
import com.example.weatherapp.ui.theme.dailyItemSecondColor


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CityItem(
    cityData: CityData,
    onClick: () -> Unit,
    onDelete: (CityData) -> Unit,
    composition: LottieComposition?,
    isDeletable: Boolean = true
) {

    var longPressState by remember { mutableStateOf(false) }

    Box(modifier = Modifier
        .background(
            brush = Brush.horizontalGradient(
                colors = if (cityData.isCurrent) listOf(
                    currentCityCornerColor, currentItemSecondColor
                ) else listOf(
                    dailyItemCornerColor, backgroundColor
                )
            ), shape = RoundedCornerShape(cornerRadius_32)
        )
        .combinedClickable(
            onClick = {
                if (longPressState)
                    longPressState = false
                else onClick()
            }, onLongClick = {
                if (isDeletable)
                    longPressState = true
            })
        .fillMaxWidth()
        .height(card_Sizes_170)
    ) {

        Box(
            Modifier
                .padding(top = padding_3, start = padding_3)
                .fillMaxSize()
                .align(Alignment.BottomEnd)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = if (cityData.isCurrent) listOf(
                            currentCityItemColor, currentItemSecondColor
                        ) else listOf(
                            dailyItemSecondColor, backgroundColor
                        )
                    ), shape = RoundedCornerShape(cornerRadius_32)
                )
        )
        Column(
            Modifier
                .padding(padding_16)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${cityData.currentTemperature}°",
                    fontSize = fontSize_34,
                    color = colorWhite
                )
                Image(
                    painter = painterResource(id = cityData.conditionData!!.icon),
                    contentDescription = "",
                    modifier = Modifier.size(imageSize_58),
//                    contentScale = ContentScale.Crop
                )
            }
            Column(Modifier.fillMaxWidth()) {
                Text(
                    text = "H:${cityData.temperatureMax}° L:${
                        cityData.temperatureMin
                    }°",
                    color = if (cityData.isCurrent) currentItemTempColor else placeHolderColor,
                    fontSize = fontSize_12
                )
                if (longPressState) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = if (cityData.cityName.length < 12) cityData.cityName else {
                                cityData.cityName.substring(0, 12)
                            },
                            color = colorWhite,
                            fontSize = fontSize_15,
                            maxLines = 1,
                        )

                        LottieAnimation(
                            modifier = Modifier
                                .size(lottieSize_24)
                                .clickable { onDelete(cityData) },
                            composition = composition,
                            isPlaying = true,
                            iterations = 2
                        )
                    }
                } else {
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        item {
                            Text(
                                text = "${cityData.cityName}, ${cityData.country}",
                                color = colorWhite,
                                fontSize = fontSize_15,
                                maxLines = 1,
                            )
                        }
                    }
                }
            }
        }
    }
}