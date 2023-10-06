package com.example.weatherapp.app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.smallItemBackgroundColor
import com.example.weatherapp.ui.theme.smallItemCornerColor
import com.example.weatherapp.ui.theme.textColorDate

@Composable
fun SmallItem(text: String, temperature: String) {

    Column(
        modifier = Modifier.padding(end = padding_22),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(vertical = padding_16),
            text = text,
            fontSize = fontSize_15,
            color = textColorDate
        )

        Box(
            modifier = Modifier
                .background(smallItemCornerColor, shape = CircleShape)
                .size(cardSize_52)
        ) {
            Card(
                Modifier
                    .size(cardSize_50)
                    .align(Alignment.BottomEnd),
                shape = CircleShape,
                colors = CardDefaults.cardColors(containerColor = smallItemBackgroundColor)
            ) {}
            Image(
                modifier = Modifier
                    .size(imageSize_28)
                    .align(Alignment.Center),
                painter = painterResource(id = R.drawable.moon_cloud_mid_rain),
                contentDescription = ""
            )
        }

        Text(
            modifier = Modifier.padding(padding_8),
            text = temperature,
            fontSize = fontSize_15,
            color = textColorDate
        )
    }
}