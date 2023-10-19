package com.example.weatherapp.app.screens.location

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.weatherapp.R
import com.example.weatherapp.app.screens.imageSize_24
import com.example.weatherapp.app.screens.padding_10
import com.example.weatherapp.app.screens.padding_16
import com.example.weatherapp.domain.model.CityData
import com.example.weatherapp.ui.theme.colorWhite
import com.example.weatherapp.ui.theme.currentItemTempColor
import com.example.weatherapp.ui.theme.placeHolderColor

@Composable
fun CityNameItem(cityData: CityData, modifier: Modifier = Modifier) {

    Row(
        modifier = modifier.padding(vertical = padding_10),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .padding(end = padding_16)
                .size(imageSize_24),
            painter = painterResource(id = R.drawable.location),
            contentDescription = ""
        )
        Column {
            Text(text = cityData.cityName + ", " + cityData.region, color = colorWhite)
            Text(text = cityData.country, color = placeHolderColor,modifier=Modifier.fillMaxWidth())
        }
    }
}