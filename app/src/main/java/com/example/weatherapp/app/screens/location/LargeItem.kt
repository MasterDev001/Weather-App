package com.example.weatherapp.app.screens.location

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import com.example.weatherapp.R
import com.example.weatherapp.app.screens.card_Sizes_170
import com.example.weatherapp.app.screens.cornerRadius_32
import com.example.weatherapp.app.screens.fontSize_12
import com.example.weatherapp.app.screens.fontSize_15
import com.example.weatherapp.app.screens.fontSize_28
import com.example.weatherapp.app.screens.imageSize_76
import com.example.weatherapp.app.screens.padding_16
import com.example.weatherapp.app.screens.padding_3
import com.example.weatherapp.domain.model.CityData
import com.example.weatherapp.ui.theme.backgroundColor
import com.example.weatherapp.ui.theme.colorWhite
import com.example.weatherapp.ui.theme.placeHolderColor
import com.example.weatherapp.ui.theme.xLargeItemCornerColor
import com.example.weatherapp.ui.theme.xLargeItemSecondColor

@Composable
fun LargeItem(cityData: CityData, onClick: () -> Unit) {

    Box(modifier = Modifier
        .background(
            brush = Brush.horizontalGradient(
                colors = listOf(
                    xLargeItemCornerColor, backgroundColor
                )
            ), shape = RoundedCornerShape(cornerRadius_32)
        )
        .clickable { onClick() }
        .fillMaxWidth()
        .height(card_Sizes_170)) {

        Box(
            Modifier
                .padding(top = padding_3, start = padding_3, end = padding_3)
                .fillMaxSize()
                .align(Alignment.BottomEnd)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            xLargeItemSecondColor, backgroundColor
                        )
                    ), shape = RoundedCornerShape(cornerRadius_32)
                )
        )
        Column(
            Modifier
                .padding(start = padding_16, bottom = padding_16)
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
                    fontSize = fontSize_28,
                    color = colorWhite
                )
                Image(
                    painter = painterResource(id = cityData.weatherType!!.iconRes),
                    contentDescription = "",
                    modifier = Modifier.size(imageSize_76),
//                    contentScale = ContentScale.Crop
                )
            }
            Column(Modifier.fillMaxWidth()) {
                Text(
                    text = "H:${cityData.temperatureMax}° L:${
                        cityData.temperatureMin
                    }°", color = placeHolderColor, fontSize = fontSize_12
                )
                LazyRow {
                    item {
                        Text(
                            text = "${cityData.cityName}, ${cityData.countryCode}",
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